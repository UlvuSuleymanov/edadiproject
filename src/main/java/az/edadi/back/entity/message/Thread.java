package az.edadi.back.entity.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "thread")
    private List<UserThread> userThread=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thread")
    private List<Message> messages = new ArrayList<>();

}
