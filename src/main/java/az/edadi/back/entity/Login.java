package az.edadi.back.entity;

import az.edadi.back.entity.message.Thread;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private Date date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    public Login(User user){
        this.user=user;
        this.date= new Date();
        this.ip= AuthUtil.getcurrentIp();
    }
}

