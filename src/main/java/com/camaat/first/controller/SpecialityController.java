package com.camaat.first.controller;

import com.camaat.first.service.SpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/speciality")
public class SpecialityController {
    private  final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }


    @GetMapping()
    ResponseEntity getUniSpecialities(@RequestParam String abbr,@RequestParam Integer group){

            return ResponseEntity.ok(specialityService.getSpecialitiesOfUni(abbr,group));
    }

}
