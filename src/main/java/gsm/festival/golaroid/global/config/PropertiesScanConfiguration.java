package gsm.festival.golaroid.global.config;

import gsm.festival.golaroid.thirparty.aws.s3.config.S3Property;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationPropertiesScan(
        basePackageClasses = S3Property.class
)
public class PropertiesScanConfiguration {
}