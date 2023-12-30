package az.edadi.back.entity.message;

import az.edadi.back.entity.auth.User;
import az.edadi.back.model.request.MessageRequestModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 500)
    private String text;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Thread thread;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;


    public Message(MessageRequestModel messageRequestModel) {
        text = messageRequestModel.getContent();
        date = new Date();
    }

}
