package az.edadi.back.controller;

import az.edadi.back.model.response.SpecialityResponseModel;
import az.edadi.back.model.response.SpecialitySummaryResModel;
import az.edadi.back.service.SpecialityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/speciality")
public class SpecialityController {
    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @GetMapping
    ResponseEntity<List<SpecialitySummaryResModel>> getSpecialities(@RequestParam String group){
        return ResponseEntity.ok(specialityService.getSpecialityList(Long.valueOf(group)));
    }

    @GetMapping("/{code}")
    ResponseEntity<SpecialityResponseModel> getSpecialit(@PathVariable Long code) {
        return ResponseEntity.ok(specialityService.getSpeciality(code));
    }

//    @GetMapping(value = "/api/university/{uniId}/speciality")
//    ResponseEntity getUniversitySpecialities(@PathVariable Long uniId, @RequestParam String group) {
//        return ResponseEntity.ok(specialityService.getUniversitySpecialitiesWithGroup(uniId, Long.valueOf(group)));
//    }

}
