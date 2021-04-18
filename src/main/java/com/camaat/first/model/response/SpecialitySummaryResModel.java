package com.camaat.first.model.response;

import com.camaat.first.entity.university.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SpecialitySummaryResModel {
    private Long id;
    private  String nameAz;
    private  String nameEn;
    private  String code;

    public SpecialitySummaryResModel(Speciality uniSpeciality){
        id=uniSpeciality.getId();
        nameAz=uniSpeciality.getNameAz();
        nameEn=uniSpeciality.getNameEn();
        code=uniSpeciality.getCode();
    }


}
