package gsm.festival.golaroid.thirdparty.feign.error;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import gsm.festival.golaroid.global.exception.InternalServerError;
import gsm.festival.golaroid.thirdparty.feign.error.exception.InsufficientCreditsException;
import gsm.festival.golaroid.thirdparty.feign.error.exception.MissionApiException;
import gsm.festival.golaroid.thirdparty.feign.error.exception.MultipleSourceException;
import gsm.festival.golaroid.thirdparty.feign.error.exception.RateLimitExceededException;

public class FeignClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400)
            switch (response.status()) {
                case 400:
                    throw new MultipleSourceException();
                case 402:
                    throw new InsufficientCreditsException();
                case 403:
                    throw new MissionApiException();
                case 429:
                    throw new RateLimitExceededException();
                default:
                    throw new InternalServerError();
            }

        return FeignException.errorStatus(methodKey, response);
    }
}
