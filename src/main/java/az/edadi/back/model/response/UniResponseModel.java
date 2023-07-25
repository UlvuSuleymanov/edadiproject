package az.edadi.back.model.response;

import az.edadi.back.entity.university.University;
import az.edadi.back.utility.PhotoUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor

public class UniResponseModel {
    private Long id;
    private String abbr;
    private String nameEn;
    private String nameAz;
    private String url;
    private String info;

    public UniResponseModel(University university) {
        this.id = university.getId();
        this.abbr = university.getAbbr();
        this.nameAz = university.getNameAz();
        this.nameEn = university.getNameEn();
        this.info = university.getInfo();
        this.url = PhotoUtil.getUniversityPhoto(university.getPhotoUrl());
    }

}
