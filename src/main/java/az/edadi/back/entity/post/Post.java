package az.edadi.back.entity.post;

import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.app.Question;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.PostRequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import jakarta.persistence.*;

import java.util.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String text;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private University university;

    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "post")
    private List<FileItem> image = new ArrayList<>();


    //    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Vote> votes = new ArrayList<>();


    //    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();


    public Post(PostRequestModel postRequestModel, User user) {
        text = postRequestModel.getText();
        date = new Date();
        this.user = user;
    }

}
