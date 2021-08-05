package az.edadi.back.entity.roommate;

import az.edadi.back.entity.User;
import az.edadi.back.model.request.RoommateRequestModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RoommateAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String info;

    private Integer amount;

    private Date date;

    private Long size;

    private String phone;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;


    public  RoommateAd(RoommateRequestModel roommateRequestModel){
        title=roommateRequestModel.getTitle();
        info=roommateRequestModel.getInfo();
        amount=roommateRequestModel.getAmount();
        date=new Date();
        size=roommateRequestModel.getRoomSize();
        phone=roommateRequestModel.getNumber();
    }

}
