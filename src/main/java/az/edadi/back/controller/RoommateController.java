package az.edadi.back.controller;

import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.RoomMateRepository;
import az.edadi.back.service.RoomMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "api/roommate")
public class RoommateController {
    private final RoomMateService roomMateService;

    @Autowired
    public RoommateController(RoomMateService roomMateService) {
        this.roomMateService = roomMateService;
    }


    @PostMapping
    ResponseEntity addRoomAd(@RequestBody RoommateRequestModel roommateRequestModel){
       RoommateResponseModel roommateResponseModel= roomMateService.addRoommate(roommateRequestModel);

       return ResponseEntity.ok(roommateResponseModel);
    }

    @GetMapping
    List<RoommateResponseModel> getRoommateAds(@RequestParam(required = false) String roommateSort){
         return roomMateService.getRoommates(1,1);
    }

}
