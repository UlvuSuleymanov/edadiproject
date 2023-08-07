package az.edadi.back.entity;

import az.edadi.back.entity.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private UUID name;

    private String fileName;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
