package az.edadi.back.entity.app;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Roommate;
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
public class FileItem {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String fileName;

    private String type;

    private Boolean used;

    private String folder;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    private Roommate roommate;

}
