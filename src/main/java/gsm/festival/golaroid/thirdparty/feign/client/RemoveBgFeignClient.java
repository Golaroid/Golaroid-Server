package gsm.festival.golaroid.thirdparty.feign.client;

import gsm.festival.golaroid.thirdparty.feign.dto.request.RemoveBgRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "removeBgFeignClient", url = "https://api.remove.bg/v1.0")
public interface RemoveBgFeignClient {

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/removebg",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @feign.Headers("X-Api-Key: AXttUhbpj4EEorbPctHRudrM")
    MultipartFile removeBg(RemoveBgRequest removeBgRequest);
}
