package az.edadi.back.controller;

import az.edadi.back.model.request.RoommateReq;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.service.RoomMateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/roommate")
public class RoommateController {

    private final RoomMateService roomMateService;

    public RoommateController(RoomMateService roomMateService) {
        this.roomMateService = roomMateService;
    }

    @PostMapping
    ResponseEntity<HttpStatus> addRoommate(@Valid @RequestBody RoommateReq roommateRequest) {
         roomMateService.addRoommate(roommateRequest);
         return ResponseEntity.ok(HttpStatus.CREATED);
    }
    @GetMapping
    List<RoommateResponseModel> getRoommateList(@RequestParam(defaultValue = "0") Long regionId,
                                                @RequestParam(defaultValue = "1") int page) {
        return roomMateService.getRoommateList(regionId, page);
    }
    @GetMapping("{id}")
    ResponseEntity<RoommateResponseModel> getRoommate(@PathVariable Long id) {
        RoommateResponseModel roommateResponseModel = roomMateService.getRoommate(id);
        return ResponseEntity.ok(roommateResponseModel);
    }
    @DeleteMapping("{id}")
    ResponseEntity<HttpStatus> deleteRoommate(@PathVariable Long id) {
        roomMateService.deleteRoommateAd(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
