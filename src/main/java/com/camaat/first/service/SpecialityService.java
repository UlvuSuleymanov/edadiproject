package com.camaat.first.service;

import com.camaat.first.entity.Speciality;
import com.camaat.first.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {
    List<SpecialitySummaryResModel> getGeneralSpecialities();
    List<Speciality> getSpecialtiesOfUni(Long uniID);
    SpecialitySummaryResModel createSpecialitySummaryResModel(Speciality speciality);

}
