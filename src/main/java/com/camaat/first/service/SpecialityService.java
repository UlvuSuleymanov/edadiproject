package com.camaat.first.service;

import com.camaat.first.entity.Speciality;
import com.camaat.first.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {
    List<SpecialitySummaryResModel> getGeneralSpecialities();
    List<SpecialitySummaryResModel> getSpecialtiesOfUni(Long uniID);
    SpecialitySummaryResModel createSpecialitySummaryResModel(Speciality speciality);

}
