package az.edadi.back.entity.university;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialityOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     private String author;
     private String name;
     private Long userId;
     private Long uniId;
}
