package com.camaat.first.service;


import com.camaat.first.model.response.SpecialityResponseModel;
import com.camaat.first.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {

     List<SpecialitySummaryResModel> getSpecialities(String abbr,Long group);
//    SpecialityResponseModel getSpeciality(Long code);


}
