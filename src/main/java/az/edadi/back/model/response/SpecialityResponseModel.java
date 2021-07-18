package az.edadi.back.model.response;

import az.edadi.back.entity.university.Speciality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityResponseModel {
    private Long code;
    private Long id;
    private String name;
    private String universityAbbr;
    private String universityName;

    public  SpecialityResponseModel(Speciality speciality){
        code=speciality.getSpecialityCode();
        id=speciality.getId();
        name=speciality.getName();
        universityAbbr=speciality.getUniversity().getAbbr();
        universityName=speciality.getUniversity().getNameAz();
    }


}
