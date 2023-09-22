package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.roommate.Region;
import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.RegionRepository;
import az.edadi.back.repository.RoomMateRepository;
import az.edadi.back.repository.UserEventsRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.RoomMateService;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoomMateServiceImpl implements RoomMateService {

    private final RoomMateRepository roomMateRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final UserEventsRepository userEventsRepository;

    @Override
    public RoommateResponseModel addRoommate(RoommateRequestModel roommateRequestModel) {

        RoommateAd roommateAd = new RoommateAd(roommateRequestModel);
        User user = userRepository.getById(AuthUtil.getCurrentUserId());
        userEventsRepository.check(UserEvent.ADD_ROOMMATE);
        Optional<Region> region = regionRepository.findById(roommateRequestModel.getRegion());
        if (region.isPresent())
            roommateAd.setRegion(region.get());

        roommateAd.setUser(user);
        return new RoommateResponseModel(roomMateRepository.save(roommateAd));
    }

    @Override
    public List<RoommateResponseModel> getRoommates(Long regionId, int page) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("date").descending());
        List<RoommateAd> roommateAds = regionId.intValue() != 0 ?
                roomMateRepository.getRoommatesByRegion(regionId, pageable)
                :
                roomMateRepository.findAll(pageable).getContent();

        return roommateAds
                .stream()
                .map(roommateAd -> new RoommateResponseModel(roommateAd))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoommateAd> getAllRoommateAds(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("date").descending());
        List<RoommateAd> roommateAds = roomMateRepository.findAll(pageable).getContent();
        return roommateAds;
    }

    @Override
    public RoommateResponseModel getRoommate(Long id) {
        RoommateAd roommateAd = roomMateRepository
                .findById(id).orElseThrow(
                        () ->  new EntityNotFoundException("No roommate with this id")
        );
        return new RoommateResponseModel(roommateAd);
    }

    @Override
    public void deleteRoommateAd(Long id) {
        Long currentId = AuthUtil.getCurrentUserId();
        RoommateAd roommateAd = roomMateRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (roommateAd.getUser().getId().equals(currentId) || AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            roomMateRepository.delete(roommateAd);
        else
            throw new UserAuthorizationException();
    }
}
