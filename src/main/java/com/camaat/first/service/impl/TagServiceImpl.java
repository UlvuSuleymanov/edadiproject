package com.camaat.first.service.impl;

import com.camaat.first.entity.post.Tag;
import com.camaat.first.entity.post.Post;
import com.camaat.first.model.response.TagResponseModel;
import com.camaat.first.repository.TagRepository;
import com.camaat.first.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
     private final TagRepository tagRepository;

     @Autowired
     public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
     }


    @Override
    public Set<Tag>     addTags(Set<String> tagList, Post post) {

      Set<Tag> tagSet=new HashSet<>();
       tagList.stream()
                .forEach(
                        tagName -> {
                            //get post
                            Tag tag = tagRepository.findByTag(tagName.toLowerCase());

                            if(tag==null)
                            {
                                tag=tagRepository.save(new Tag(tagName.toLowerCase()));
                            }
                            tag.getPosts().add(post);

                             tagRepository.save(tag);

                             tagSet.add(tag);


                        }

                );

        return tagSet;
     }

    @Override
    public Set<Tag> getTagsByTagNames(Set<String> tagNames) {
        return  tagNames.stream()
                .map(s -> tagRepository.findByTag(s))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TagResponseModel> getTags(int size) {
        PageRequest pageable= PageRequest.of(0,size);


        List<Tag> tagList= tagRepository.getTopTags(pageable);

        Set<TagResponseModel> tagResponseModelSet =  tagList.stream()
                .map(tag -> new TagResponseModel(tag.getTag(),tag.getId(),tag.getPosts().size())

                ).collect(Collectors.toSet());
        return tagResponseModelSet;
    }


}
