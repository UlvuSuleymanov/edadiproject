package com.camaat.first.model.response;
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
    private String postTitle;
    private String postText;
    private String authorName;
    private String authorUsername;
    private String authorPhotoUrl;
    private Date date;
    private Long postLikeCount;
    private Long postCommentCount;
    private String photoUrl;
    private Boolean isLiked;


}


