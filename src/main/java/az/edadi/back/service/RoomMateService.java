package az.edadi.back.service;

import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.request.RoommateReq;
import az.edadi.back.model.response.RoommateResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomMateService {

    RoommateResponseModel addRoommate(RoommateReq roommateRequestModel);

    List<RoommateResponseModel> getRoommateList(Long regionId,int page);

    List<Roommate> getAllRoommateAds(int page);

    RoommateResponseModel getRoommate(Long id);

    void deleteRoommateAd(Long id);

}
