package com.camaat.first.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uni/{abbr}/speciality")
public class UniSpecialityController {
    @GetMapping
    ResponseEntity getUniSpecialities(@PathVariable String abbr){


    }

}
