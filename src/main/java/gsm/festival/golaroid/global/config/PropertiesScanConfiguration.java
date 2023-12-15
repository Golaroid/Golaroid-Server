package gsm.festival.golaroid.global.config;

import gsm.festival.golaroid.thirdparty.aws.property.AwsProperties;
import gsm.festival.golaroid.thirdparty.aws.s3.config.property.S3Property;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(
        basePackageClasses = {
                S3Property.class,
                AwsProperties.class
        }
)
public class PropertiesScanConfiguration {
}