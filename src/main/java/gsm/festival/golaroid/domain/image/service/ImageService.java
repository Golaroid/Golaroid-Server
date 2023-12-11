package gsm.festival.golaroid.domain.image.service;

import gsm.festival.golaroid.common.aws.AwsS3Util;
import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.exception.NotValidExtensionException;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.repository.PostRepository;
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
    private final PostRepository postRepository;

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile multipartFile) {
        List<String> allowedExtensions = List.of("jpeg", "jpg", "png");
        String fileExtension = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();

        if (!allowedExtensions.contains(fileExtension))
            throw new NotValidExtensionException();

        String code = RandomStringUtils.random(6, true, true);
        String fileName = code + fileExtension;
        String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

        Image image = Image.builder()
                .imageUrl(imageUrl)
                .post(null)
                .build();
        imageRepository.save(image);
    }
}
