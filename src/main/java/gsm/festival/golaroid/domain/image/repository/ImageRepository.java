package gsm.festival.golaroid.domain.image.repository;

import gsm.festival.golaroid.domain.image.entity.Image;
import gsm.festival.golaroid.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAllByPost(Post post);
}
