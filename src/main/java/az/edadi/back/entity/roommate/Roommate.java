package az.edadi.back.entity.roommate;

import az.edadi.back.entity.app.File;
import az.edadi.back.entity.auth.User;
import az.edadi.back.model.request.RoommateRequestModel;
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

    private String info;

    private Integer amount;

    private Date date;

    private Boolean haveHouse;

    private String contact;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Region region;

    @OneToMany(fetch = FetchType.EAGER)
    private List<File> files;



    public Roommate(RoommateRequestModel roommateRequestModel) {
        info = roommateRequestModel.getInfo();
        amount = roommateRequestModel.getAmount();
        date = new Date();
        contact = roommateRequestModel.getContact();
    }

}
