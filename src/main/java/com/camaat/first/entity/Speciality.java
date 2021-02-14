package com.camaat.first.entity;

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
    private String name;

    //type u : Speciality of the university
    //type g : General speciality
    private char type;


    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "speciality",orphanRemoval = false)
    private List<Post> postList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "speciality",orphanRemoval = false)
    private List<User> userList= new ArrayList<>();;

    @ManyToOne(fetch = FetchType.EAGER)
    private  University university;



}
