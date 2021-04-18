package com.camaat.first.entity;

import com.camaat.first.entity.post.Comment;
import com.camaat.first.entity.post.Post;
import com.camaat.first.entity.post.PostVote;
import com.camaat.first.entity.university.Speciality;
import com.camaat.first.entity.university.University;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 40)
    private  String username;

    @Column(length = 50)
    private  String name;

    @Column(unique = true, length = 40)
    private String email;

    private String password;

    private  String photoUrl;

    private Date birthDay;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality uniSpeciality;

    @ManyToOne(fetch = FetchType.LAZY)
    private University university;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Post> posts =new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Image> images =new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "user")
    private List<PostVote> postVote=new ArrayList<>();




    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authorities")
    @Enumerated(EnumType.STRING)
    private Set<UserAuthority> authorities = new HashSet<>();







    public User(String username, String name, String email, String password) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
