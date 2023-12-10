package gsm.festival.golaroid.domain.image.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage() {

    }
}
