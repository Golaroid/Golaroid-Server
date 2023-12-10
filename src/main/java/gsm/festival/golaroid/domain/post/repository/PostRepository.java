package gsm.festival.golaroid.domain.post.repository;

import gsm.festival.golaroid.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
