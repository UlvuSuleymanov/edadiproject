package az.edadi.back.entity;

import az.edadi.back.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "topic")
    private List<Post> posts =new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}