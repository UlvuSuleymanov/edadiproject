package az.edadi.back.entity;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.message.Message;
import az.edadi.back.entity.message.UserThread;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;

import az.edadi.back.entity.post.Vote;
import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.Subject;
import az.edadi.back.entity.university.TextBook;
import az.edadi.back.model.request.SignUpRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Builder
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

    private String imageName;

    private Date profileBirthDay;

    @ManyToOne
    private Speciality speciality;

    @OneToMany( cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Post> posts =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Article> articles =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<RoommateAd> roommateAds =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Image> images =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Vote> votes=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Topic> topics =new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Subject> subjects =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<TextBook> textBooks =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<Message> messages =new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "user")
    private List<UserThread> userThreads = new ArrayList<>();

    private String photoUrl;


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
        username=signUpRequestModel.getUsername().toLowerCase();
        name=signUpRequestModel.getName();
        email=signUpRequestModel.getEmail().toLowerCase();
        profileBirthDay = new Date();
        imageName= AppConstants.USER_DEFAULT_PHOTO;
    }
    public User(Long id){
        this.id=id;
    }


}
