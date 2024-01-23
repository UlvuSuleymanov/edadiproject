package az.edadi.back.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Mps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;
    private float point;

    @ManyToOne
    private Speciality speciality;
    
}
