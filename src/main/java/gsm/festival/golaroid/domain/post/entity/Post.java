package gsm.festival.golaroid.domain.post.entity;

import gsm.festival.golaroid.domain.image.entity.Image;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String code;

}
