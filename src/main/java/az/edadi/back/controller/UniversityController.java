package az.edadi.back.controller;

import az.edadi.back.service.UniversityService;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.UniRequestModel;
import az.edadi.back.model.response.UniResponseModel;
import az.edadi.back.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/university")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

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
    @Cacheable("universities")
    ResponseEntity getUniList() {
        List<UniResponseModel> uniResponseModelList = universityService.getUnisList();
        return ResponseEntity.ok(uniResponseModelList);

    }

    @GetMapping("/{abbrName}")
    ResponseEntity getUni(@PathVariable String abbrName) {
        UniResponseModel uniResponseModel = universityService.getUni(abbrName);
        return ResponseEntity.ok(uniResponseModel);
    }


}
