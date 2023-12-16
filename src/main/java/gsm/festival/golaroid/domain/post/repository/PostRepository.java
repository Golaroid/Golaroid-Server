package gsm.festival.golaroid.domain.post.repository;

import gsm.festival.golaroid.domain.post.entity.Post;
import gsm.festival.golaroid.domain.post.entity.constant.DisclosureStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByDisclosureStatus(DisclosureStatus disclosureStatus);
    Optional<Post> findByCode(String code);
}
