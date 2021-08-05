package az.edadi.back.model.response;
import az.edadi.back.entity.post.Post;
import az.edadi.back.model.MediaAuthor;
import az.edadi.back.utility.PhotoUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
    private MediaAuthor author;

    public PostResponseModel(Post post, boolean isLiked){
        id=post.getId();
//        postTitle=post.getPostTitle();
       text=post.getText();
        date=post.getDate();
        postLikeCount=post.getPostVote().size();
        postCommentCount=post.getComments().size();
        author=new MediaAuthor(post.getUser());
        this.isLiked=isLiked;

    }

}


