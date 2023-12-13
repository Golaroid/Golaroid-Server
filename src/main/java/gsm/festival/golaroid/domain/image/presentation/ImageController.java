package gsm.festival.golaroid.domain.image.presentation;

import gsm.festival.golaroid.domain.image.presentation.dto.request.UploadImageRequest;
import gsm.festival.golaroid.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Void> uploadImage(@RequestPart MultipartFile multipartFile, @RequestBody @Valid UploadImageRequest uploadImageRequest) {
        imageService.uploadImage(multipartFile, uploadImageRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Void> uploadImages(@RequestPart List<MultipartFile> multipartFilesList, @RequestBody @Valid UploadImageRequest uploadImageRequest) {
        imageService.uploadImage(multipartFilesList, uploadImageRequest);
        return ResponseEntity.ok().build();
    }
}