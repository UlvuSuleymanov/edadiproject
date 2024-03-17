package az.edadi.back.entity.app;

import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Post;
import jakarta.persistence.*;

@Entity
public class Reels extends BaseEntity {
    @OneToOne
    private User user;
    @OneToOne
    private Topic topic;
    @OneToOne
    private Post post;
}
