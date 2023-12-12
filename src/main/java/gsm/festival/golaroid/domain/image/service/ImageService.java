package gsm.festival.golaroid.domain.image.service;

import gsm.festival.golaroid.common.aws.AwsS3Util;
import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.exception.NotValidExtensionException;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.presentation.dto.request.UploadImageRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AwsS3Util awsS3Util;
    private final ImageRepository imageRepository;

    private static List<String> allowedExtensions = List.of("jpeg", "jpg", "png");

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile multipartFile, UploadImageRequest uploadImageRequest) {
        String fileExtension = isValidExtension(multipartFile);

        String code = RandomStringUtils.random(6, true, true);
        String fileName = code + fileExtension;
        String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

        Image image = Image.builder()
                .imageUrl(imageUrl)
                .post(null)
                .build();
        imageRepository.save(image);
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(List<MultipartFile> multipartFiles, UploadImageRequest uploadImageRequest) {
        multipartFiles.stream().forEach(multipartFile -> {
            String fileExtension = isValidExtension(multipartFile);

            Post post = uploadImageRequest.getIsPublic() ? createPost(uploadImageRequest.getWriter()) : null;

            String fileName = post.getCode() + fileExtension;
            String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

            Image image = Image.builder()
                    .imageUrl(imageUrl)
                    .post(null)
                    .build();
            imageRepository.save(image);
        });
    }


    private String isValidExtension(MultipartFile multipartFile) {
        String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        if (!allowedExtensions.contains(fileExtension))
            throw new NotValidExtensionException();

        return fileExtension;
    }

    private Post createPost(String writer) {
        String code = RandomStringUtils.random(6, true, true);
        Post post = Post.builder()
                .writer(writer)
                .code(code)
                .build();

        return post;
    }
}