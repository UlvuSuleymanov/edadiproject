package az.edadi.back.controller;

 import az.edadi.back.model.response.StudyMaterialRes;
import az.edadi.back.service.StudyMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/study-materials")
public class SharedStudyMaterialController {

    private final StudyMaterialService studyMaterialService;

    @GetMapping
    ResponseEntity<List<StudyMaterialRes>> getStudyMaterials(@RequestParam int page) {
        return ResponseEntity.ok(studyMaterialService.getStudyMaterialList(page));
    }
}
