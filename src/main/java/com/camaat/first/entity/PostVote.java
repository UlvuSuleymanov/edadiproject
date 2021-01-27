package com.camaat.first.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private  User user;

    private Date date;




}
