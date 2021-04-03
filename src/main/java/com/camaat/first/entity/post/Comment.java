package com.camaat.first.entity.post;

import com.camaat.first.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false,length = 400)
    private  String commentText;


    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
     private User user;

    @ManyToOne(fetch = FetchType.EAGER)
     private Post post;

    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "comment")
    private List<CommentVote> commentVotes = new ArrayList<>();



}
