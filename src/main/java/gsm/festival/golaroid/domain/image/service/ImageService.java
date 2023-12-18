package gsm.festival.golaroid.domain.image.service;

import gsm.festival.golaroid.common.aws.AwsS3Util;
import gsm.festival.golaroid.common.removebg.RemoveBgUtil;
import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.exception.NotValidExtensionException;
import gsm.festival.golaroid.domain.image.presentation.dto.request.UploadImageRequest;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.post.entity.constant.DisclosureStatus;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AwsS3Util awsS3Util;
    private final ImageRepository imageRepository;
    private final RemoveBgUtil removeBgUtil;

    private static List<String> allowedExtensions = List.of("jpeg", "jpg", "png");

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile multipartFile, UploadImageRequest uploadImageRequest) {
        String fileExtension = isValidExtension(multipartFile);

        Post post = uploadImageRequest.getIsPublic() ?
                createPost(uploadImageRequest.getWriter(), DisclosureStatus.PUBLIC) : createPost(uploadImageRequest.getWriter(), DisclosureStatus.PRIVATE);

        String fileName = post.getCode() + "." + fileExtension;
        String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

        saveImage(imageUrl, post);
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadImages(List<MultipartFile> multipartFiles, UploadImageRequest uploadImageRequest) {
        multipartFiles.stream().forEach(multipartFile -> {
            String fileExtension = isValidExtension(multipartFile);

            Post post = uploadImageRequest.getIsPublic() ?
                    createPost(uploadImageRequest.getWriter(), DisclosureStatus.PUBLIC) : createPost(uploadImageRequest.getWriter(), DisclosureStatus.PRIVATE);

            String fileName = post.getCode() + "." + fileExtension;
            String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

            byte[] responseEntity = removeBgUtil.removeBackground(imageUrl);

            awsS3Util.deleteImage(fileName);
            awsS3Util.uploadImage(convertToMultipartFile(responseEntity, fileName), fileName);

            saveImage(imageUrl, post);
        });
    }

    private String isValidExtension(MultipartFile multipartFile) {
        String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        if (!allowedExtensions.contains(fileExtension))
            throw new NotValidExtensionException();

        return fileExtension;
    }

    private Post createPost(String writer, DisclosureStatus disclosureStatus) {
        String code = RandomStringUtils.random(6, true, true);
        Post post = Post.builder()
                .writer(writer)
                .code(code)
                .disclosureStatus(disclosureStatus)
                .build();

        return post;
    }

    private void saveImage(String imageUrl, Post post) {
        Image image = Image.builder()
                .imageUrl(imageUrl)
                .post(post)
                .build();
        imageRepository.save(image);
    }

    private MultipartFile convertToMultipartFile(byte[] byteArray, String fileName) {
        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return "file";
            }

            @Override
            public String getOriginalFilename() {
                return fileName;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return byteArray.length;
            }

            @Override
            public byte[] getBytes() {
                return byteArray;
            }

            @Override
            public InputStream getInputStream() {
                return new ByteArrayInputStream(byteArray);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                Files.write(dest.toPath(), byteArray);
            }
        };

        return multipartFile;
    }
}