package az.edadi.back.controller;

 import az.edadi.back.service.SpecialityService;
 import az.edadi.back.service.UniversityService;
 import az.edadi.back.entity.university.University;
 import az.edadi.back.model.request.UniRequestModel;
 import az.edadi.back.model.response.UniResponseModel;
 import az.edadi.back.model.response.UniSummaryModel;
 import az.edadi.back.repository.UniversityRepository;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.access.prepost.PreAuthorize;
 import org.springframework.web.bind.annotation.*;
 import java.util.List;

@RestController
@RequestMapping("/api/university")
public class UniController {
    private  final UniversityRepository universityRepository;
    private  final UniversityService universityService;
    private final SpecialityService uniSpecialityService;
    public UniController(UniversityRepository universityRepository,
                         UniversityService universityService,
                         SpecialityService uniSpecialityService) {

        this.universityRepository = universityRepository;
        this.universityService = universityService;
        this.uniSpecialityService = uniSpecialityService;
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
     List<UniSummaryModel> uniResponseModelList = universityService.getUnisList();
     return ResponseEntity.ok(uniResponseModelList);

    }

    @GetMapping("/{abbrName}")
    ResponseEntity getUni(@PathVariable String abbrName){
     UniResponseModel uniResponseModel = universityService.getUni(abbrName);
    return ResponseEntity.ok(uniResponseModel);
    }






}
