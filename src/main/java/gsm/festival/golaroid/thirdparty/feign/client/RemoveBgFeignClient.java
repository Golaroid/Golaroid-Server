package gsm.festival.golaroid.thirdparty.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "removeBgFeignClient", url = "https://api.remove.bg/v1.0")
public class RemoveBgFeignClient {
}
