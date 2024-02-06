package az.edadi.back.entity.roommate;

import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.request.RoommateReq;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Roommate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Boolean haveHouse;

    private String houseInfo;

    private String authorGender;

    private String contact;

    private String generalInfo;

    private Date date;

    private Date lastUpdate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;


    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "roommate")
    private List<FileItem> fileItems =new ArrayList<>();


    @PreUpdate
    public void preUpdate() {
         this.date = new Date();
    }

    public Roommate(RoommateReq roommateRequestModel) {
        haveHouse=roommateRequestModel.getHaveHouse();
        houseInfo=roommateRequestModel.getHouseInfo();
        authorGender=roommateRequestModel.getSex();
        contact=roommateRequestModel.getContact();
        generalInfo= roommateRequestModel.getHouseInfo();
        date=new Date();
        lastUpdate=new Date();
    }

}
