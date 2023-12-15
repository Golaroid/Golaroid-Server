package gsm.festival.golaroid.thirdparty.feign.error;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import gsm.festival.golaroid.global.exception.InternalServerError;

public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() >= 400)
            throw new InternalServerError();

        return FeignException.errorStatus(methodKey, response);
    }
}
