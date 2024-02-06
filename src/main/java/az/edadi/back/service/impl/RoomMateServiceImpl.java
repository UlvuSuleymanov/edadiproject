package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.app.FileItem;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.roommate.Region;
import az.edadi.back.entity.roommate.Roommate;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.exception.model.UserNotFoundException;
import az.edadi.back.model.request.RoommateReq;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.*;
import az.edadi.back.service.RoomMateService;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RoomMateServiceImpl implements RoomMateService {

    private final RoomMateRepository roomMateRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final UserEventsRepository userEventsRepository;
    private final FileItemRepository fileItemRepository;

    public RoomMateServiceImpl(RoomMateRepository roomMateRepository,
                               RegionRepository regionRepository,
                               UserRepository userRepository,
                               UserEventsRepository userEventsRepository,
                               FileItemRepository fileItemRepository) {
        this.roomMateRepository = roomMateRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
        this.userEventsRepository = userEventsRepository;
        this.fileItemRepository = fileItemRepository;
    }


    @Override
    public RoommateResponseModel addRoommate(RoommateReq roommateRequestModel) {
        userEventsRepository.check(UserEvent.ADD_ROOMMATE);
        Roommate roommate = new Roommate(roommateRequestModel);

        User user = userRepository.findById(AuthUtil.getCurrentUserId()).orElseThrow(
                UserNotFoundException::new
        );

        Region region = regionRepository.findById(roommateRequestModel.getRegionId()).orElseThrow(
                EntityNotFoundException::new
        );
        List<FileItem> images = new ArrayList<>();

        if (roommateRequestModel.getHaveHouse()) {
            images = fileItemRepository.findByIds(roommateRequestModel.getUrls());
            fileItemRepository.saveAll(images);
            roommate.setFileItems(images);
        }


        roommate.setUser(user);
        roommate.setRegion(region);
        Roommate savedRoommate = roomMateRepository.saveAndFlush(roommate);


        if (roommateRequestModel.getHaveHouse()) {
            images = images.stream().map(file -> {file.setUsed(true); file.setRoommate(savedRoommate); return file;}).toList();
            fileItemRepository.saveAll(images);
            roommate.setFileItems(images);
        }

        System.out.println(roommateRequestModel.getUrls());
        return new RoommateResponseModel(roomMateRepository.save(roommate));
    }

    @Override
    public List<RoommateResponseModel> getRoommates(Long regionId, int page) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("date").descending());
        List<Roommate> roommates = regionId.intValue() != 0 ?
                roomMateRepository.getRoommatesByRegion(regionId, pageable)
                :
                roomMateRepository.findAll(pageable).getContent();

        return roommates
                .stream()
                .map(roommateAd -> new RoommateResponseModel(roommateAd))
                .collect(Collectors.toList());
    }

    @Override
    public List<Roommate> getAllRoommateAds(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("date").descending());
        List<Roommate> roommates = roomMateRepository.findAll(pageable).getContent();
        return roommates;
    }

    @Override
    public RoommateResponseModel getRoommate(Long id) {
        Roommate roommate = roomMateRepository
                .findById(id).orElseThrow(
                        () -> new EntityNotFoundException("No roommate with this id")
                );
        return new RoommateResponseModel(roommate);
    }

    @Override
    public void deleteRoommateAd(Long id) {
        Long currentId = AuthUtil.getCurrentUserId();
        Roommate roommate = roomMateRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (roommate.getUser().getId().equals(currentId) || AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            roomMateRepository.delete(roommate);
        else
            throw new UserAuthorizationException();
    }
}
