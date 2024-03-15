package az.edadi.back.service;

import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.UniRequestModel;
import az.edadi.back.model.response.UniversityResponseModel;

import java.util.List;

public interface UniversityService {

    University createUni(UniRequestModel uniRequestModel);

    List<UniversityResponseModel> getUnisList();

    UniversityResponseModel getUni(Long id);

}
