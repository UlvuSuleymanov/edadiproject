package az.edadi.back.service.impl;

import az.edadi.back.model.response.StudyMaterialRes;
import az.edadi.back.repository.StudyMaterialRepository;
import az.edadi.back.service.StudyMaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyMaterialServiceImpl implements StudyMaterialService {

    private final StudyMaterialRepository studyMaterialRepository;
    @Override
    public List<StudyMaterialRes> getStudyMaterialList(int page) {
        return null;
    }
}
