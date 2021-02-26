package com.camaat.first.service;

import com.camaat.first.entity.university.Speciality;
import com.camaat.first.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {
    List<SpecialitySummaryResModel> getGeneralSpecialities();
    SpecialitySummaryResModel createSpecialitySummaryResModel(Speciality speciality);

}
