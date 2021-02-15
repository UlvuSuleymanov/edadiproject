package com.camaat.first.controller;

 import com.camaat.first.entity.University;
 import com.camaat.first.model.request.UniRequestModel;
 import com.camaat.first.model.response.UniResponseModel;
 import com.camaat.first.repository.UniversityRepository;
 import com.camaat.first.service.UniversityService;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequestMapping("/api/uni")
public class UniController {
    private  final UniversityRepository universityRepository;
    private  final UniversityService universityService;
    public UniController(UniversityRepository universityRepository, UniversityService universityService) {
        this.universityRepository = universityRepository;
        this.universityService = universityService;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('admin:update')")
    ResponseEntity addUniversity(@RequestBody UniRequestModel uniRequestModel){
        University university = universityService.createUni(uniRequestModel);
        universityRepository.save(university);
        return  new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping
    ResponseEntity getUniList(){
     List<UniResponseModel> uniResponseModelList = universityService.getUnisList();
     return ResponseEntity.ok(uniResponseModelList);

    }
    @GetMapping("/{abbrName}")
    ResponseEntity getUni(@PathVariable String abbrName){

    return new ResponseEntity(HttpStatus.OK);
    }

}
