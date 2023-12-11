package gsm.festival.golaroid.thirparty.aws.property;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.credentials")
public class AwsProperties {
    private final String accessKey;
    private final String secretKey;
}