package az.edadi.back.entity.roommate;

import az.edadi.back.entity.User;
import az.edadi.back.model.request.RoommateRequestModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RoommateAd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    private Integer amount;

    private Date date;

    private String contact;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;


    public RoommateAd(RoommateRequestModel roommateRequestModel) {
        info = roommateRequestModel.getInfo();
        amount = roommateRequestModel.getAmount();
        date = new Date();
        contact = roommateRequestModel.getContact();
    }

}
