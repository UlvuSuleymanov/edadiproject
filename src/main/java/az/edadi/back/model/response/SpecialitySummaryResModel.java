package az.edadi.back.model.response;

import az.edadi.back.entity.university.Speciality;
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
    private String name;
    private Long code;
    private Long group;
    private String university;
    private String type;

    public SpecialitySummaryResModel(Speciality uniSpeciality) {
        id = uniSpeciality.getId();
        name = uniSpeciality.getName();
        code = uniSpeciality.getSpecialityCode();
        group = uniSpeciality.getSpecialityGroup();
        university = uniSpeciality.getUniversity().getAbbrAz();
        type = uniSpeciality.getType();
    }


}
