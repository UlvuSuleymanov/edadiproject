package com.camaat.first.entity.university;

import com.camaat.first.entity.User;
import com.camaat.first.entity.post.Post;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true)
    private String abbr;

    private String abbrAz;
    private String nameAz;
    private String nameEn;

    private String photoUrl;
    private String info;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<Post> postList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<User> userList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<Speciality> specialities= new ArrayList<>();;

}
