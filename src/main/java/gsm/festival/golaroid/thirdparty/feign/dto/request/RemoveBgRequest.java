package gsm.festival.golaroid.thirdparty.feign.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class RemoveBgRequest {

    @NotBlank
    private final String imageUrl;

    @NotBlank
    private final String size = "auto";

    @NotBlank
    private final String type = "auto";

    @NotBlank
    private final String typeLevel = "1";

    @NotBlank
    private final String format = "auto";

    @NotBlank
    private final String roi = "0% 0% 100% 100%";

    @NotBlank
    private final Boolean crop = false;

    @NotBlank
    private final String cropMargin = "0";

    @NotBlank
    private final String scale = "original";

    @NotBlank
    private final String position = "original";

    @NotBlank
    private final String channels = "rgba";

    @NotBlank
    private final Boolean addShadow = false;

    @NotBlank
    private final Boolean semiTransparency = true;

    @NotBlank
    private final String bgColor = "";

    @NotBlank
    private final String bgImageUrl ="";

    @Builder
    public RemoveBgRequest(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}