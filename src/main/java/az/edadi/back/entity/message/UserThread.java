package az.edadi.back.entity.message;

import az.edadi.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserThread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Thread thread;

}
