package az.edadi.back.controller;

import az.edadi.back.model.response.SpecialitySummaryResModel;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.service.SpecialityService;
import az.edadi.back.entity.university.Speciality;
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
     ResponseEntity getSpecialities(@RequestParam Long group){

            return ResponseEntity.ok(specialityService.getSpecialities(group));
    }

    @GetMapping("/{code}")
    ResponseEntity getSpecialit(@PathVariable Long code){
     Optional<Speciality> speciality=  specialityRepository.findBySpecialityCode(code);


     if(speciality.isPresent())
        return ResponseEntity.ok(new SpecialitySummaryResModel(speciality.get()));


        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
