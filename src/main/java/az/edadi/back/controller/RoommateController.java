package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.res.RoommateResponseRecord;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.service.RoomMateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/roommate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoommateController {
    private final RoomMateService roomMateService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping
    ResponseEntity addRoomAd(@RequestBody RoommateRequestModel roommateRequestModel) {
        applicationEventPublisher.publishEvent(UserEvent.ADD_ROOMMATE);
        RoommateResponseModel roommateResponseModel = roomMateService.addRoommate(roommateRequestModel);
        return ResponseEntity.ok(roommateResponseModel);
    }

    @GetMapping
    List<RoommateResponseModel> getRoommateAds(@RequestParam(defaultValue = "0") Long regionId,
                                               @RequestParam(defaultValue = "1") int page) {

        return roomMateService.getRoommates(regionId, page);
    }


    @GetMapping("/{id}")
    ResponseEntity deleteRoommateAd(@PathVariable Long id) {
      RoommateResponseModel roommateResponseModel= roomMateService.getRoommate(id);
        return ResponseEntity.ok(roommateResponseModel);
    }

    @DeleteMapping("/{id}")
    ResponseEntity getRoommate(@PathVariable Long id) {
        roomMateService.deleteRoommateAd(id);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity getAllRoommateAds(@RequestParam(defaultValue = "0") int page) {
        List<Roommate> roommates = roomMateService.getAllRoommateAds(page);
        List<RoommateResponseRecord> roommateResponseRecords = roommates.stream().map(
                roommateAd -> new RoommateResponseRecord(roommateAd)
        ).collect(Collectors.toList());
        return ResponseEntity.ok(roommateResponseRecords);
    }
}
