package az.edadi.back.service;


import az.edadi.back.model.response.SpecialitySummaryResModel;

import java.util.List;

public interface SpecialityService {

     List<SpecialitySummaryResModel> getSpecialities(String abbr,Long group);
//    SpecialityResponseModel getSpeciality(Long code);


}
