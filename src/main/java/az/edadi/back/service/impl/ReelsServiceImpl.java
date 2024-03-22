package az.edadi.back.service.impl;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.app.Reels;
import az.edadi.back.entity.app.Topic;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.response.ReelsRes;
import az.edadi.back.repository.ReelsRepository;
import az.edadi.back.service.ReelsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImpl implements ReelsService {

    private final ReelsRepository reelsRepository;

    public ReelsServiceImpl(ReelsRepository reelsRepository) {
        this.reelsRepository = reelsRepository;
    }

    @Override
    public List<ReelsRes> getReels(int page) {
        return reelsRepository.findAll().stream().map(ReelsRes::new).toList();
    }

    @Override
    public void saveReels(Post post) {
        Reels reels = new Reels();
        reels.setPost(post);
        reels.setType(EntityType.POST);
        reelsRepository.save(reels);
    }

    @Override
    public void saveReels(Topic topic) {
        Reels reels = new Reels();
        reels.setTopic(topic);
        reels.setType(EntityType.TOPIC);
        reelsRepository.save(reels);
    }

    @Override
    public void saveReels(Roommate roommate) {
        Reels reels = new Reels();
        reels.setRoommate(roommate);
        reels.setType(EntityType.ROOMMATE);
        reelsRepository.save(reels);
    }
}
