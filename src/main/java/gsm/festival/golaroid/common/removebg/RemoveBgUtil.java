package gsm.festival.golaroid.common.removebg;

import gsm.festival.golaroid.domain.image.presentation.dto.request.RemoveBgRequest;
import gsm.festival.golaroid.thirdparty.removebg.property.RemoveBgProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RemoveBgUtil {

    private final RemoveBgProperty removeBgProperty;

    public byte[] removeBackground(String imageUrl) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", removeBgProperty.getApiKey());
        headers.setContentType(MediaType.APPLICATION_JSON);

        RemoveBgRequest requestBody = RemoveBgRequest.builder()
                .imageUrl(imageUrl)
                .build();

        HttpEntity<RemoveBgRequest> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.postForObject("https://api.remove.bg/v1.0/removebg", requestEntity, byte[].class);
    }
}
