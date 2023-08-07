package az.edadi.back.entity.university;

import az.edadi.back.entity.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.textbook.TextbookAd;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private Long specialityCode;

    private Long specialityGroup;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<User> users = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<MinEntryPoint> minEntryPoints = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "speciality")
    private List<TextbookAd> textbookAds = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    private University university;



}
