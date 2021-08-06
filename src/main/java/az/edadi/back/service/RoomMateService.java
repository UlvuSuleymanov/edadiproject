package az.edadi.back.service;

import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.response.RoommateResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomMateService {

    RoommateResponseModel addRoommate(RoommateRequestModel roommateRequestModel);

    List<RoommateResponseModel> getRoommates(Long regionId,int page);


}
