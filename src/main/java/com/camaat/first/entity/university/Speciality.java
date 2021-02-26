package com.camaat.first.entity.university;

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

public class Speciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameAze;
    private String nameEng;



    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "speciality",orphanRemoval = false)
    private List<Post> postList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "speciality",orphanRemoval = false)
    private List<UniSpeciality> uniSpecialityList=new ArrayList<>();


//    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "speciality",orphanRemoval = false)
//    private List<User> userList= new ArrayList<>();;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    private  University university;



}
