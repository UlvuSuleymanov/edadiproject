package az.edadi.back.entity.message;

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
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    private boolean  isActive;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Room room;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "thread")
    private List<Message> messages = new ArrayList<>();

    public Thread() {
        date=new Date();
        isActive=true;
    }
}
