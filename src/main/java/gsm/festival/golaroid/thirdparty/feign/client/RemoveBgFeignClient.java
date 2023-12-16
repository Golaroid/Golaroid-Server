package gsm.festival.golaroid.thirdparty.feign.client;

import gsm.festival.golaroid.thirdparty.feign.dto.request.RemoveBgRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "removeBgFeignClient", url = "https://api.remove.bg/v1.0")
public interface RemoveBgFeignClient {

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/removebg",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    byte[] removeBg(@RequestHeader("X-Api-Key") String headers, @RequestBody RemoveBgRequest removeBgRequest);
}
