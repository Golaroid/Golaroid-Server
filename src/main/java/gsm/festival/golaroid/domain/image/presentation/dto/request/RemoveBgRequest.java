package gsm.festival.golaroid.domain.image.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveBgRequest {

    private final String image_file_b64;

    private final String image_url;

    private final String size;

    private final String type;

    private final String type_level;

    private final String format;

    private final String roi;

    private final Boolean crop;

    private final String crop_margin;

    private final String scale;

    private final String position;

    private final String channels;

    private final Boolean add_shadow;

    private final Boolean semiTransparency;

    private final String bg_color;

    private final String bg_image_url;

    @Builder
    public RemoveBgRequest(String imageUrl) {
        this.image_file_b64 = "";
        this.image_url = imageUrl;
        this.size = "auto";
        this.type = "auto";
        this.type_level = "1";
        this.format = "auto";
        this.roi = "0% 0% 100% 100%";
        this.crop = false;
        this.crop_margin = "0";
        this.scale = "original";
        this.position = "original";
        this.channels = "rgba";
        this.add_shadow = false;
        this.semiTransparency = true;
        this.bg_color = "";
        this.bg_image_url ="";
    }
}