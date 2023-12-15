package gsm.festival.golaroid.thirdparty.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(
        basePackages = "gsm.festival.golaroid.thirdparty.feign"
)
public class FeignConfig {
}
