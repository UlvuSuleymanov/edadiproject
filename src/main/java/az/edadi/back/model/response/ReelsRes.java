package az.edadi.back.model.response;

import az.edadi.back.constants.type.EntityType;
import az.edadi.back.entity.app.Reels;
import az.edadi.back.entity.app.Topic;
import az.edadi.back.entity.post.Post;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.UserSummary;
import lombok.Data;

@Data
public class ReelsRes {
    private Long reelsId;
    private Long entityId;
    private String type;
    private String title;
    private String date;
    private String parentPage;
    private String entityPage;
    private UserSummary user;

    public ReelsRes(Reels reels) {
        EntityType type = reels.getType();
        switch (type) {
            case POST -> this.setPost(reels.getPost());
            case ROOMMATE -> this.setRoommate(reels.getRoommate());
            case TOPIC -> this.setTopic(reels.getTopic());
        }
        this.setType(reels.getType().toString());
        this.reelsId=reels.getId();

    }


    private void setPost(Post post) {
        PostResponseModel postResponseModel = new PostResponseModel(post,false);
        this.entityId=postResponseModel.getId();
        this.title=postResponseModel.getText();
        this.date=postResponseModel.getDate();
        this.parentPage=postResponseModel.getParentPath();
        this.user=postResponseModel.getAuthor();
    }

    private void setTopic(Topic topic) {
        TopicResponse topicResponse = new TopicResponse(topic);
        this.entityId=topicResponse.getId();
        this.title=topicResponse.getTitle();
        this.date=topicResponse.getDate();
        this.user=topicResponse.getUser();
        parentPage=EntityType.TOPIC.getPage();
        entityPage=parentPage+"/"+topicResponse.getSlug();
    }

    private void setRoommate(Roommate roommate) {
        RoommateResponseModel roommateResponseModel = new RoommateResponseModel(roommate);
        this.entityId=roommateResponseModel.getId();
        this.title=roommateResponseModel.getInfo();
        this.date=roommateResponseModel.getDate();
        this.user=roommateResponseModel.getAuthor();
        this.parentPage=EntityType.ROOMMATE.getPage();
        this.entityPage=parentPage+"/"+roommate.getId();
    }

}
