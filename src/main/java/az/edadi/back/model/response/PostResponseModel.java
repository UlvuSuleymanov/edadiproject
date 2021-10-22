package az.edadi.back.model.response;

import az.edadi.back.entity.post.Post;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostResponseModel {
    private Long id;
    private String text;
    private Date date;
    private int postLikeCount;
    private int postCommentCount;
    private String photoUrl;
    private Boolean isLiked;
    private UserSummary author;
    private boolean canDelete;

    public PostResponseModel(Post post, boolean isLiked) {
        id = post.getId();
//        postTitle=post.getPostTitle();
        text = post.getText();
        date = post.getDate();
        postLikeCount = post.getVotes().size();
        postCommentCount = post.getComments().size();
        author = new UserSummary(post.getUser());
        this.isLiked = isLiked;
        canDelete = AuthUtil.userIsAuthenticated() && post.getUser().getId().equals(AuthUtil.getCurrentUserId());


    }

}


