package gsm.festival.golaroid.domain.image.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Transactional(rollbackFor = Exception.class)
    public void uploadImage(MultipartFile multipartFile) {

    }
}
