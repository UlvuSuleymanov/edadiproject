package az.edadi.back.service;

import az.edadi.back.entity.app.Topic;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.response.ReelsRes;

import java.util.List;

public interface ReelsService {
    List<ReelsRes> getReels(int page);
    void saveReels(Post post);
    void saveReels(Topic post);
    void saveReels(Roommate post);
}
