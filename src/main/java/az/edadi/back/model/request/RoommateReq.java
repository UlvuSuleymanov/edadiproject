package az.edadi.back.model.request;

import org.hibernate.validator.constraints.Length;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class RoommateReq {

     private Boolean haveHouse;

    private String houseInfo;

    private List<UUID> urls = new ArrayList<>();

    @Length(min = 1, max = 10)
    private String sex;

    private String contact;

    private Long regionId;

    @Length(min = 1, max = 255)
    private String generalInfo;

    public RoommateReq(Boolean haveHouse, String houseInfo, List<UUID> urls, String sex, String contact, Long regionId, String generalInfo) {
        this.haveHouse = haveHouse;
        this.houseInfo = houseInfo;
        this.urls = urls;
        this.sex = sex;
        this.contact = contact;
        this.regionId = regionId;
        this.generalInfo = generalInfo;
    }

    public RoommateReq() {
    }


    public Boolean getHaveHouse() {
        return haveHouse;
    }

    public void setHaveHouse(Boolean haveHouse) {
        this.haveHouse = haveHouse;
    }

    public String getHouseInfo() {
        return houseInfo;
    }

    public void setHouseInfo(String houseInfo) {
        this.houseInfo = houseInfo;
    }

    public List<UUID> getUrls() {
        return urls;
    }

    public void setUrls(List<UUID> urls) {
        this.urls = urls;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }
}
