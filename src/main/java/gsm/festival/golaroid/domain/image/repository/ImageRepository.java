package gsm.festival.golaroid.domain.image.repository;

import gsm.festival.golaroid.domain.image.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {
}
