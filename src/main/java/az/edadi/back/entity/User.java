package az.edadi.back.entity;

import az.edadi.back.constants.UserEnum;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.PostVote;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.SignUpRequestModel;
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

    private Date profileBirthDay;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality speciality;

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

    public User(SignUpRequestModel signUpRequestModel){
        username=signUpRequestModel.getUsername();
        name=signUpRequestModel.getName();
        email=signUpRequestModel.getEmail();
        profileBirthDay = new Date();
        photoUrl= UserEnum.DEFAULT_IMAGE_NAME.getImageName();
    }


}
