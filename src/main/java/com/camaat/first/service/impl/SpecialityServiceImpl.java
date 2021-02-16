package com.camaat.first.service.impl;

import com.camaat.first.entity.Speciality;
import com.camaat.first.model.response.SpecialitySummaryResModel;
import com.camaat.first.repository.SpecialityRepository;
import com.camaat.first.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private  final SpecialityRepository specialityRepository;
    @Autowired
    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }


    @Override
    public List<SpecialitySummaryResModel> getGeneralSpecialities() {
         List<Speciality> specialityList = specialityRepository.findByUniversityIsNull();

       return   specialityList.stream()
                 .map(speciality -> createSpecialitySummaryResModel(speciality))
                 .collect(Collectors.toList());
    }

    @Override
    public List<Speciality> getSpecialtiesOfUni(Long uniID) {
        return null;
    }

    @Override
    public SpecialitySummaryResModel createSpecialitySummaryResModel(Speciality speciality) {
        SpecialitySummaryResModel specialitySummaryResModel = new SpecialitySummaryResModel();

      return    specialitySummaryResModel.setId(speciality.getId())
                .setName(speciality.getName());
    }
}
