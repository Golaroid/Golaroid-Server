package gsm.festival.golaroid.domain.post.repository;

import gsm.festival.golaroid.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
}
