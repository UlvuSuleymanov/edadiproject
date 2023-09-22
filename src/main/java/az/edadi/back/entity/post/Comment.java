package az.edadi.back.entity.post;

import az.edadi.back.entity.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import jakarta.persistence.*;

 import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false,length = 400)
    private  String commentText;


    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
     private User user;

    @ManyToOne(fetch = FetchType.EAGER)
     private Post post;


    @LazyCollection(LazyCollectionOption.EXTRA)
    @OneToMany(fetch =FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "comment")
    private List<Vote> votes=new ArrayList<>();


}
