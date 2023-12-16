package gsm.festival.golaroid.thirdparty.removebg.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "removebg")
public class RemoveBgProperty {
    private final String apiKey;
}
