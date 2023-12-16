package gsm.festival.golaroid.domain.image.service;

import gsm.festival.golaroid.common.aws.AwsS3Util;
import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.image.exception.NotValidExtensionException;
import gsm.festival.golaroid.domain.image.presentation.dto.request.UploadImageRequest;
import gsm.festival.golaroid.domain.image.repository.ImageRepository;
import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.thirdparty.feign.client.RemoveBgFeignClient;
import gsm.festival.golaroid.thirdparty.feign.dto.request.RemoveBgRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import org.springframework.http.HttpHeaders;
import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final AwsS3Util awsS3Util;
    private final ImageRepository imageRepository;
    private final RemoveBgFeignClient removeBgFeignClient;

    private static List<String> allowedExtensions = List.of("jpeg", "jpg", "png");

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile multipartFile, UploadImageRequest uploadImageRequest) {
        String fileExtension = isValidExtension(multipartFile);

        Post post = uploadImageRequest.getIsPublic() ? createPost(uploadImageRequest.getWriter()) : null;

        String fileName = post.getCode() + "." + fileExtension;
        String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

        Image image = Image.builder()
                .imageUrl(imageUrl)
                .post(post)
                .build();
        imageRepository.save(image);
    }

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(List<MultipartFile> multipartFiles, UploadImageRequest uploadImageRequest) {
        RestTemplate restTemplate = new RestTemplate();
        multipartFiles.stream().forEach(multipartFile -> {
            String fileExtension = isValidExtension(multipartFile);

            Post post = uploadImageRequest.getIsPublic() ? createPost(uploadImageRequest.getWriter()) : null;

            String fileName = post.getCode() + "." + fileExtension;
            String imageUrl = awsS3Util.uploadImage(multipartFile, fileName);

            RemoveBgRequest removeBgRequest = RemoveBgRequest.builder()
                    .imageUrl(imageUrl)
                    .build();

            System.out.println("==================");
            System.out.println(removeBgRequest.getImage_url());
            System.out.println("==================");
            System.out.println(imageUrl);
            System.out.println(fileName);
//            byte[] removeBgImage = removeBgFeignClient.removeBg("AVEqsh55ubEj7mn7pBQmqzZF", removeBgRequest);

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", "AVEqsh55ubEj7mn7pBQmqzZF");
            headers.setContentType(MediaType.APPLICATION_JSON);

            RemoveBgRequest requestBody = RemoveBgRequest.builder()
                    .imageUrl(imageUrl)
                    .build();

            HttpEntity<RemoveBgRequest> requestEntity = new HttpEntity<>(requestBody, headers);
            byte[] responseEntity = restTemplate.postForObject("https://api.remove.bg/v1.0/removebg", requestEntity, byte[].class);

            awsS3Util.deleteImage(fileName);
//            System.out.println(awsS3Util.uploadImage(convertToMultipartFile(removeBgImage, fileName), fileName));
            awsS3Util.uploadImage(convertToMultipartFile(responseEntity, fileName), fileName);

            Image image = Image.builder()
                    .imageUrl(imageUrl)
                    .post(post)
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

    public MultipartFile convertToMultipartFile(byte[] byteArray, String fileName) {
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
            public byte[] getBytes() throws IOException {
                return byteArray;
            }

            @Override
            public InputStream getInputStream() throws IOException {
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