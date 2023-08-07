package az.edadi.back.entity.message;

import az.edadi.back.entity.User;
import az.edadi.back.model.request.MessageRequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", length = 500)
    private String text;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Thread thread;

    public Message(MessageRequestModel messageRequestModel) {
        text = messageRequestModel.getMessage();
        date = new Date();
    }

}
