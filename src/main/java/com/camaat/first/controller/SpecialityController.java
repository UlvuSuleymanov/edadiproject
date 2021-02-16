package com.camaat.first.controller;

import com.camaat.first.model.response.SpecialitySummaryResModel;
import com.camaat.first.service.SpecialityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/speciality")
public class SpecialityController {
    private  final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    ResponseEntity getGeneralSpecialityList(){
    List<SpecialitySummaryResModel> specialitySummaryResModelList=specialityService.getGeneralSpecialities();
    return ResponseEntity.ok(specialitySummaryResModelList);
    }

    ResponseEntity getUniSpecialityList(){

        return new ResponseEntity(HttpStatus.OK);
    }

}
