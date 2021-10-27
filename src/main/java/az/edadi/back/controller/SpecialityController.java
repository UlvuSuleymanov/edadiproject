package az.edadi.back.controller;

import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.service.SpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpecialityController {
    private final SpecialityService specialityService;
    private final SpecialityRepository specialityRepository;

    public SpecialityController(SpecialityService specialityService, SpecialityRepository specialityRepository) {
        this.specialityService = specialityService;
        this.specialityRepository = specialityRepository;
    }


    @GetMapping(value = "/api/speciality")
    ResponseEntity getSpecialities(@RequestParam String group) throws InterruptedException {
        return ResponseEntity.ok(specialityService.getSpecialities(Long.valueOf(group)));
    }

    @GetMapping("/api/speciality/{code}")
    ResponseEntity getSpecialit(@PathVariable Long code) {
        return ResponseEntity.ok(specialityService.getSpeciality(code));
    }


    @GetMapping(value = "/api/university/{uniId}/speciality")
    ResponseEntity getUniversitySpecialities(@PathVariable Long uniId, @RequestParam String group) {
        return ResponseEntity.ok(specialityService.getUniversitySpecialitiesWithGroup(uniId, Long.valueOf(group)));
    }


}
