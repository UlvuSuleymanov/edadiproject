package az.edadi.back.model.response;

import az.edadi.back.entity.post.Comment;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseModel {
    private Long id;
    private String text;
    private int likeCount;
    private String date;
    private UserSummary author;
    private boolean isLiked;

    public CommentResponseModel(Comment comment, boolean isLiked) {
        this.id = comment.getId();
        this.text = comment.getCommentText();
        this.likeCount = comment.getVotes().size();
        this.date = DateUtil.getHowLongAgoString(comment.getDate());
        this.author = new UserSummary(comment.getUser());
        this.isLiked = isLiked;
    }
}
