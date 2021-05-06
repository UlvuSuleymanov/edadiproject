package az.edadi.back.entity.post;

import az.edadi.back.entity.Article;
import lombok.AllArgsConstructor;
 import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, unique = true)
    private String tag;


    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();


    @LazyCollection(LazyCollectionOption.EXTRA)
    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();



    public Tag(String tag) {
        this.tag = tag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }



    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
