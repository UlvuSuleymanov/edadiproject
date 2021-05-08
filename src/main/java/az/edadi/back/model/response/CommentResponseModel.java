package az.edadi.back.model.response;

import az.edadi.back.entity.post.Comment;
import az.edadi.back.utility.ImageUtil;
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
    private  Long commentId;
    private  String commentText;
    private int likeCount;
    private Date birthDay;
    private String authorPhotoUrl;
    private  String author;
    private boolean isLiked;

    public CommentResponseModel(Comment comment, boolean isLiked){
        this.commentId=comment.getId();
        this.commentText=comment.getCommentText();
        this.likeCount=comment.getCommentVotes().size();
        this.birthDay=comment.getDate();
        this.authorPhotoUrl= ImageUtil.getPhotoUrl(comment.getUser().getPhotoUrl());
        this.author=comment.getUser().getUsername();
        this.isLiked=isLiked;
    }
}