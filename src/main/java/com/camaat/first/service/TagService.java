package com.camaat.first.service;
import com.camaat.first.entity.post.Tag;
import com.camaat.first.entity.post.Post;
import com.camaat.first.model.response.TagResponseModel;

import java.util.Set;

public interface TagService {
     Set<Tag> addTags(Set<String> tagList, Post post);
     Set<Tag> getTagsByTagNames(Set<String> tagNames);
     Set<TagResponseModel> getTags(int size);
}
