package az.edadi.back.entity.app;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.BaseEntity;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Roommate;
import jakarta.persistence.*;

@Entity
public class Reels extends BaseEntity {
    private EntityType type;
    @OneToOne
    private User user;
    @OneToOne
    private Topic topic;
    @OneToOne
    private Post post;
    @OneToOne
    private Roommate roommate;
}
