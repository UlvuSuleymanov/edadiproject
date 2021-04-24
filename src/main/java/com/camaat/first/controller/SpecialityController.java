package com.camaat.first.controller;

import com.camaat.first.entity.university.Speciality;
import com.camaat.first.model.response.SpecialitySummaryResModel;
import com.camaat.first.repository.SpecialityRepository;
import com.camaat.first.service.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/speciality")
public class SpecialityController {
    private  final SpecialityService specialityService;
    private  final SpecialityRepository specialityRepository;

    public SpecialityController(SpecialityService specialityService, SpecialityRepository specialityRepository) {
        this.specialityService = specialityService;
        this.specialityRepository = specialityRepository;
    }


    @GetMapping
    ResponseEntity getSpecialities(@RequestParam String abbr, @RequestParam Long group){

            return ResponseEntity.ok(specialityService.getSpecialities(abbr,group));
    }

    @GetMapping("/{code}")
    ResponseEntity getSpecialit(@PathVariable Long code){
     Optional<Speciality> speciality=  specialityRepository.findBySpecialityCode(code);


     if(speciality.isPresent())
        return ResponseEntity.ok(new SpecialitySummaryResModel(speciality.get()));


        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
