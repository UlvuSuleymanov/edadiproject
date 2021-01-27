package com.camaat.first.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestModel {
    private String postTitle;
    private String postText;
    private Set<String> tags;
    private MultipartFile multipartFile;

}
