package az.edadi.back.model.response;
import az.edadi.back.entity.post.Post;
import az.edadi.back.utility.ImageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PostResponseModel {
    private Long id;
    private String postTitle;
    private String postText;
    private String authorName;
    private String authorUsername;
    private String authorPhotoUrl;
    private Date date;
    private int postLikeCount;
    private int postCommentCount;
    private String photoUrl;
    private Boolean isLiked;

    public PostResponseModel(Post post, boolean isLiked){
        id=post.getId();
        postTitle=post.getPostTitle();
        postText=post.getPostText();
        authorName=post.getUser().getName();
        authorUsername=post.getUser().getUsername();
        authorPhotoUrl= ImageUtil.getPhotoUrl(post.getUser().getPhotoUrl());
        date=post.getDate();
        postLikeCount=post.getPostVote().size();
        postCommentCount=post.getComments().size();

        if(post.getPhotoUrl()!=null&& post.getPhotoUrl().equals("default")) {
            photoUrl =  post.getPhotoUrl();
        }

        else{
            photoUrl = ImageUtil.getPhotoUrl(post.getPhotoUrl());
        }

        this.isLiked=isLiked;

    }

}


