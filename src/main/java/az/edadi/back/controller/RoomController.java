package az.edadi.back.controller;

import az.edadi.back.model.response.RoomRes;
import az.edadi.back.service.RoomService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    RoomRes getRoom(@RequestParam Long userId){
       return roomService.getRoom(userId);

    }
}
