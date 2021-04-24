package com.camaat.first.model.response;

import com.camaat.first.entity.university.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityResponseModel {
    private Long code;
    private Long id;
    private String nameAz;
    private String nameEn;
    private String university_abbr;
    private String university_name;

    public  SpecialityResponseModel(Speciality speciality){
        code=speciality.getSpecialityCode();
        id=speciality.getId();
        nameAz=speciality.getNameAz();
        nameEn=speciality.getNameEn();
        university_abbr=speciality.getUniversity().getAbbr();
        university_name=speciality.getUniversity().getNameAz();
    }


}
