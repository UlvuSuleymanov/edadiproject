package com.camaat.first.controller;

import com.camaat.first.service.UniSpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/uni/{abbr}/speciality")
public class UniSpecialityController {
    private  final UniSpecialityService uniSpecialityService;

    public UniSpecialityController(UniSpecialityService uniSpecialityService) {
        this.uniSpecialityService = uniSpecialityService;
    }


    @GetMapping
    ResponseEntity getUniSpecialities(@PathVariable String abbr){

            return ResponseEntity.ok(uniSpecialityService.getSpecialitiesOfUni(abbr));
    }

}
