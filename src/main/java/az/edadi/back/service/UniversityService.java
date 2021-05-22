package az.edadi.back.service;

import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.UniRequestModel;
 import az.edadi.back.model.response.UniResponseModel;
import az.edadi.back.model.response.UniSummaryModel;

import java.util.List;

public interface UniversityService {
    University createUni(UniRequestModel uniRequestModel);
    List<UniResponseModel> getUnisList();

//    UniSummaryModel getUniSummaryResponseModel(University university);


    UniResponseModel getUni(String abbrName);
    boolean  existsById(Long id);
}
