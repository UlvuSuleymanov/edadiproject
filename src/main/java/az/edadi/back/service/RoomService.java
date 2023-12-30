package az.edadi.back.service;

import az.edadi.back.model.response.RoomRes;

public interface RoomService {
    RoomRes getRoom(Long userId);
}
