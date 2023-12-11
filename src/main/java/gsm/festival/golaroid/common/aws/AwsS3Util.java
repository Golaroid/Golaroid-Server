package gsm.festival.golaroid.common.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import gsm.festival.golaroid.thirparty.aws.s3.config.property.S3Property;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class AwsS3Util {

    private final AmazonS3 amazonS3;
    private final S3Property s3Property;

    public String uploadImage(MultipartFile multipartFile, String fileName) {
        inputS3(multipartFile, fileName);
        return queryImageUrl(fileName);
    }

    public void deleteImage(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(s3Property.getBucket(), fileName));
    }

    private void inputS3(MultipartFile multipartFile, String fileName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try {
            amazonS3.putObject(new PutObjectRequest(s3Property.getBucket(), fileName,
                    multipartFile.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.", e);
        }
    }

    public String queryImageUrl(String fileName) {
        return amazonS3.getUrl(s3Property.getBucket(), fileName).toString();
    }
}