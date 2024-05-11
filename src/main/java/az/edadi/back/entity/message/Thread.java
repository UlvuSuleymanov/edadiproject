package az.edadi.back.entity.message;

import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Thread extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Conversation conversation;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "thread")
    private List<Message> messages = new ArrayList<>();

    public Thread(Conversation conversation, User user) {
      this.user = user;
      this.conversation=conversation;
    }
}
