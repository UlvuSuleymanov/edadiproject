package az.edadi.back.entity.app;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Tag;
import az.edadi.back.model.request.ArticleRequestModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String title;

    private String slug;

    private String description;

    private int status = 1;

    @Lob
    private String content;

    private Date date;

    private String coverUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "article_tag",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();


    public Article(ArticleRequestModel articleRequestModel) {
        title = articleRequestModel.getTitle();
        content = articleRequestModel.getBody();
        date = new Date();
        description = articleRequestModel.getDescription();

    }


}
