package gsm.festival.golaroid.domain.post.entity;

import gsm.festival.golaroid.domain.post.entity.constant.DisclosureStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private DisclosureStatus disclosureStatus;

    @Builder
    public Post(String writer, String code, DisclosureStatus disclosureStatus) {
        this.writer = writer;
        this.code = code;
        this.disclosureStatus = disclosureStatus;
    }
}
