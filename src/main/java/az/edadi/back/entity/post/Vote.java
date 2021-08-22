package az.edadi.back.entity.post;

import az.edadi.back.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comment comment;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Date date;

   public  Vote(User user, Comment comment){
       this.user=user;
       this.comment=comment;
       this.date=new Date();
   }
    public  Vote(User user, Post post){
        this.user=user;
        this.post=post;
        this.date=new Date();
    }


}
