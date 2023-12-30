package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.message.Room;
import az.edadi.back.entity.message.Thread;
import az.edadi.back.model.request.RoomReq;
import az.edadi.back.model.response.RoomRes;
import az.edadi.back.repository.RoomRepository;
import az.edadi.back.repository.ThreadRepository;
import az.edadi.back.service.MessageService;
import az.edadi.back.service.RoomService;
import az.edadi.back.utility.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final ThreadRepository threadRepository;
    private final RoomRepository roomRepository;
    private final MessageService messageService;

    @Override
    public RoomRes getRoom(Long userId) {

        Long currentUserId = AuthUtil.getCurrentUserId();
        Optional<Room> room = roomRepository.getCommonRoom(Arrays.asList(userId, currentUserId));

        if (room.isEmpty()) {
            Room newRoom = roomRepository.saveAndFlush(new Room());

            Thread threadForTarget = new Thread();
            threadForTarget.setUser(new User(userId));
            threadForTarget.setRoom(newRoom);

            Thread threadForCurrent = new Thread();
            threadForCurrent.setUser(new User(AuthUtil.getCurrentUserId()));
            threadForCurrent.setRoom(newRoom);

            threadRepository.saveAllAndFlush(Arrays.asList(threadForCurrent, threadForTarget));
            return new RoomRes(newRoom, Collections.emptyList());

        } else {

            return new RoomRes(room.get(), messageService.getMessages(room.get().getId(), 0));
        }

    }
}
