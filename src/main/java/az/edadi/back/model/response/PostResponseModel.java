package az.edadi.back.model.response;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.post.Post;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import az.edadi.back.utility.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PostResponseModel {
    private Long id;
    private String text;
    private String date;
    private int likeCount;
    private int commentCount;
    private Boolean isLiked;
    private UserSummary author;
    private boolean canDelete;

    public PostResponseModel(Post post, boolean isLiked) {
        id = post.getId();
        text = post.getText();
        date = DateUtil.getHowLongAgoString(post.getDate());
        likeCount = post.getVotes().size();
        commentCount = post.getComments().size();
        author = new UserSummary(post.getUser());
        this.isLiked = isLiked;
        canDelete = AuthUtil.userIsAuthenticated() && (
                post.getUser().getId().equals(AuthUtil.getCurrentUserId()) || AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE)
        );

    }

}


