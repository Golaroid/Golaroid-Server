package gsm.festival.golaroid.thirparty.aws.s3.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties("cloud.aws.s3")
public class S3Property {
    private final String bucket;
}
