package az.edadi.back.service;


import az.edadi.back.model.response.SpecialityResponseModel;
import az.edadi.back.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {

     List<SpecialitySummaryResModel> getSpecialityList(Long group);

     List<SpecialitySummaryResModel> getUniversitySpecialities(Long uniId);

     List<SpecialitySummaryResModel> getUniversitySpecialitiesWithGroup(Long uniId,Long group);

     SpecialityResponseModel getSpeciality(Long code);



}
