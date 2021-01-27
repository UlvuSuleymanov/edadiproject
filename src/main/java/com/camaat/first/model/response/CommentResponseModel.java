package com.camaat.first.model.response;

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
}
