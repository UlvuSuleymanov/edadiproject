package az.edadi.back.entity.message;

import az.edadi.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text",length = 500)
    private String text;

    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private ChatRoom chatRoom;





}
