package az.edadi.back.controller;

import az.edadi.back.service.UniversityService;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.UniRequestModel;
import az.edadi.back.model.response.UniversityResponseModel;
import az.edadi.back.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/university")
public class UniversityController {
    private final UniversityRepository universityRepository;
    private final UniversityService universityService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity addUniversity(@RequestBody UniRequestModel uniRequestModel) {
        University university = universityService.createUni(uniRequestModel);
        universityRepository.save(university);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping
    ResponseEntity getUniList() {
        List<UniversityResponseModel> universityResponseModelList = universityService.getUnisList();
        return ResponseEntity.ok(universityResponseModelList);

    }

    @GetMapping("/{id}")
    ResponseEntity getUni(@PathVariable Long id) {
        UniversityResponseModel universityResponseModel = universityService.getUni(id);
        return ResponseEntity.ok(universityResponseModel);
    }


}
