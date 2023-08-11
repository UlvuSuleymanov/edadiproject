package az.edadi.back.controller;

import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.res.RoommateResponseRecord;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.RoomMateRepository;
import az.edadi.back.service.RoomMateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    List<RoommateResponseModel> getRoommateAds(@RequestParam(defaultValue = "0") Long regionId,
                                               @RequestParam(defaultValue = "1") int page){

         return roomMateService.getRoommates(regionId,page);
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteRoommateAd(@PathVariable Long id){
          roomMateService.deleteRoommateAd(id);
          return   ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity getAllRoommateAds(@RequestParam(defaultValue = "0") int page) {
        List<RoommateAd> roommateAds = roomMateService.getAllRoommateAds(page);
        List<RoommateResponseRecord> roommateResponseRecords = roommateAds.stream().map(
                roommateAd -> new RoommateResponseRecord(roommateAd)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(roommateResponseRecords);
    }
}
