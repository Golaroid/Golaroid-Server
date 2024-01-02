package gsm.festival.golaroid.domain.image.entity;

import gsm.festival.golaroid.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String imageUrl;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Image(String imageUrl, Post post) {
        this.imageUrl = imageUrl;
        this.post = post;
    }
}
