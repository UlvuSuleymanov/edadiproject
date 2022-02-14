package az.edadi.back.model.response;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class PostResponseModel {
    private Long id;
    private String text;
    private Date date;
    private int likeCount;
    private int commentCount;
    private Boolean isLiked;
    private UserSummary author;
    private boolean canDelete;

    public PostResponseModel(Post post, boolean isLiked) {
        id = post.getId();
        text = post.getText();
        date = post.getDate();
        likeCount = post.getVotes().size();
        commentCount = post.getComments().size();
        author = new UserSummary(post.getUser());
        this.isLiked = isLiked;
        canDelete = AuthUtil.userIsAuthenticated() && post.getUser().getId().equals(AuthUtil.getCurrentUserId());

    }

}


