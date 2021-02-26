package com.camaat.first.entity.post;

import com.camaat.first.entity.User;
import com.camaat.first.entity.post.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Date date;

}
