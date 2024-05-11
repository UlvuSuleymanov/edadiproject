package az.edadi.back.entity.message;

import az.edadi.back.entity.BaseEntity;
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
public class Message extends BaseEntity {

    @Column(name = "text", length = 500)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private Thread thread;

    @ManyToOne(fetch = FetchType.EAGER)
    private Conversation conversation;


    public Message(MessageRequestModel messageRequestModel) {
        text = messageRequestModel.getContent();
    }

}
