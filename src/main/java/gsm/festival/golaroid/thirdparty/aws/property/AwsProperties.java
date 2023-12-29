package gsm.festival.golaroid.thirdparty.aws.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws.credentials")
public class AwsProperties {
    private final String accessKey;
    private final String secretKey;
}