package az.edadi.back.service;

import az.edadi.back.model.response.StudyMaterialRes;

import java.util.List;

public interface StudyMaterialService {
    List<StudyMaterialRes> getStudyMaterialList(int page);
}
