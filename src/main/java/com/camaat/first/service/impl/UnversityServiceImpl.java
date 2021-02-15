package com.camaat.first.service.impl;

import com.camaat.first.entity.University;
import com.camaat.first.model.request.UniRequestModel;
import com.camaat.first.model.response.UniResponseModel;
import com.camaat.first.repository.UniversityRepository;
import com.camaat.first.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UnversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public UnversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public University createUni(UniRequestModel uniRequestModel) {
         University university = new University();
         university.setAbbrName(uniRequestModel.getAbbrName());
         university.setInfo(uniRequestModel.getInfo());
         university.setName(uniRequestModel.getName());
         return university;
    }

    @Override
    public List<UniResponseModel> getUnisList() {
        List<University> universityList = universityRepository.findAll();
        List<UniResponseModel> uniResponseModels=universityList
                .stream()
                .map(university -> getUniResponseModel(university))
                .collect(Collectors.toList());

     return uniResponseModels;

    }


    @Override
    public UniResponseModel getUniResponseModel(University university) {
        UniResponseModel uniResponseModel = new UniResponseModel();
       return   uniResponseModel.setAbbrName(university.getAbbrName())
                .setId(university.getId())
                .setInfo(university.getInfo());
    }
}
