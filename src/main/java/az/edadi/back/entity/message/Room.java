package az.edadi.back.entity.message;

 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isActive;

    private Date data;

    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "room")
    private List<Thread> threads;

    public Room(){
        data = new Date();
        isActive=true;
    }

}
