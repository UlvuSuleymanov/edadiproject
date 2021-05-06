package az.edadi.back.service;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.post.Tag;
import az.edadi.back.model.response.TagResponseModel;

import java.util.Set;

public interface TagService {
     Set<Tag> addTags(Set<String> tagList, Post post);
     Set<Tag> getTagsByTagNames(Set<String> tagNames);
     Set<TagResponseModel> getTags(int size);
}
