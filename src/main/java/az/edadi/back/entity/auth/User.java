package az.edadi.back.entity.auth;

import az.edadi.back.constants.AppConstants;
import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.app.*;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.entity.post.Comment;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Vote;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.entity.textbook.TextbookAd;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.Subject;
import az.edadi.back.model.request.SignUpRequestModel;
import az.edadi.back.model.response.OAuth2CustomUser;
import az.edadi.back.utility.UserUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(unique = true, nullable = false, length = 40)
    private String username;

    @Column(length = 50)
    private String name;

    @Column(unique = true, length = 40)
    private String email;

    private String password;

    private String picture;

    private String provider;

    private Date lastSeen;

    private boolean isShownLastSeen;

    @ManyToOne
    private Speciality speciality;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Roommate> roommates = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<FileItem> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Vote> votes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Topic> topics = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Subject> subjects = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Thread> userThreads = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<TextbookAd> textbookAds = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Login> logins = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<SharedStudyMaterial> sharedStudyMaterialList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authorities")
    @Enumerated(EnumType.STRING)
    private Set<UserAuthority> authorities = new HashSet<>();

    public User() {
        this.lastSeen = new Date();
        this.authorities.add(UserAuthority.USER_READ);
        this.authorities.add(UserAuthority.USER_UPDATE);
    }

//    public User(String username, String name, String email, String password) {
//        this.username = username;
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }
//


    public User(SignUpRequestModel signUpRequestModel) {
        this();
        username = signUpRequestModel.getUsername().toLowerCase();
        name = signUpRequestModel.getName();
        email = signUpRequestModel.getEmail().toLowerCase();
        picture = AppConstants.ROOT_IMAGE_URL + AppConstants.USER_IMAGE_FOLDER + AppConstants.USER_DEFAULT_PHOTO;
        provider = "NATIVE";
    }

    public User(OAuth2CustomUser user) {
        this();
        name = user.getName();
        username = UserUtil.getRandomUsername();
        email = user.getEmail();
        provider = user.getProvider();
        picture = user.getPicture();
    }

    public User(Long id) {
        super.setId(id);
    }


}
