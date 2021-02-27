package com.camaat.first.service;


 import com.camaat.first.entity.university.UniSpeciality;
 import com.camaat.first.model.response.SpecialitySummaryResModel;

 import java.util.List;

public interface UniSpecialityService {

    SpecialitySummaryResModel createUniSpecialityResponseModel(UniSpeciality uniSpeciality);
    List<SpecialitySummaryResModel> getSpecialitiesOfUni(String abbr);

 }
