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

public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String abbrName;
    private String name;
    private String info;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<Post> postList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<Speciality> specialityList= new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false, mappedBy = "university")
    private List<User> userList= new ArrayList<>();;


}
