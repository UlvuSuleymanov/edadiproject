package az.edadi.back.controller;

import az.edadi.back.entity.university.Subject;
import az.edadi.back.model.SummaryModel;
import az.edadi.back.model.request.SubjectRequestModel;
import az.edadi.back.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/subject")
public class SubjectController {

    private final SubjectService subjectService;
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    ResponseEntity getSubjects(@RequestParam Long specialityId){
        return ResponseEntity.ok(subjectService.getSubjects(specialityId));
    }

    @PostMapping
    ResponseEntity addSubject(@RequestBody @Valid SubjectRequestModel requestModel){
      Subject subject= subjectService.addSubject(requestModel);
        return  ResponseEntity.ok(new SummaryModel(subject.getId(),subject.getName()));
    }

}
