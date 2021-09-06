package az.edadi.back.model.response;

import az.edadi.back.entity.post.Comment;
import az.edadi.back.model.MediaAuthor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseModel {
    private Long commentId;
    private String commentText;
    private int likeCount;
    private Date birthDay;
    private MediaAuthor author;
    private boolean isLiked;

    public CommentResponseModel(Comment comment, boolean isLiked) {
        this.commentId = comment.getId();
        this.commentText = comment.getCommentText();
        this.likeCount = comment.getVotes().size();
        this.birthDay = comment.getDate();
        this.author = new MediaAuthor(comment.getUser());
        this.isLiked = isLiked;
    }
}
