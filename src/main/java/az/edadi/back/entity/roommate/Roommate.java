package az.edadi.back.entity.roommate;

import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.app.Reels;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.request.RoommateReq;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Roommate extends BaseEntity {

    private Boolean haveHouse;

    private String houseInfo;

    private String authorGender;

    private String contact;

    private String generalInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roommate")
    private List<FileItem> fileItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "roommate")
    private Reels reels;

    public Roommate(RoommateReq roommateRequestModel) {
        haveHouse = roommateRequestModel.getHaveHouse();
        houseInfo = roommateRequestModel.getHouseInfo();
        authorGender = roommateRequestModel.getSex();
        contact = roommateRequestModel.getContact();
        generalInfo = roommateRequestModel.getGeneralInfo();
        reels = new Reels(this);
    }

}
