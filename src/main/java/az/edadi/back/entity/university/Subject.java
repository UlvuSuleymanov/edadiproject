package az.edadi.back.entity.university;

import az.edadi.back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

}
