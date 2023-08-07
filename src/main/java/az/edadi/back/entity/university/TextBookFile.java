package az.edadi.back.entity.university;

import az.edadi.back.entity.User;
import az.edadi.back.entity.textbook.TextBookType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TextBookFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToOne(fetch = FetchType.EAGER)
    private TextBookType type;

    private String fileName;


}
