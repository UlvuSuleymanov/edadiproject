package az.edadi.back.model.response;

import az.edadi.back.entity.message.Room;
 import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class RoomRes {
    private Long id;
    private List<MessageResponseModel> messageResponseModelList;


    public RoomRes(Room room, List<MessageResponseModel> messageResponseModelList) {
        this.id = room.getId();
        this.messageResponseModelList = messageResponseModelList;
    }
}
