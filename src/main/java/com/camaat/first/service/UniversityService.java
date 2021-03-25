package com.camaat.first.service;

import com.camaat.first.entity.university.University;
import com.camaat.first.model.request.UniRequestModel;
 import com.camaat.first.model.response.UniResponseModel;
import com.camaat.first.model.response.UniSummaryModel;

import java.util.List;

public interface UniversityService {
    University createUni(UniRequestModel uniRequestModel);
    List<UniSummaryModel> getUnisList();

    UniSummaryModel getUniSummaryResponseModel(University university);


    UniResponseModel getUni(String abbrName);
    boolean  existsById(Long id);
}
