package az.edadi.back.service.impl;

import az.edadi.back.entity.User;
import az.edadi.back.entity.roommate.Region;
import az.edadi.back.entity.roommate.RoommateAd;
import az.edadi.back.model.request.RoommateRequestModel;
import az.edadi.back.model.response.RoommateResponseModel;
import az.edadi.back.repository.RegionRepository;
import az.edadi.back.repository.RoomMateRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.RoomMateService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomMateServiceImpl implements RoomMateService {
    private final RoomMateRepository roomMateRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoomMateServiceImpl(RoomMateRepository roomMateRepository, RegionRepository regionRepository, UserRepository userRepository) {
        this.roomMateRepository = roomMateRepository;
        this.regionRepository = regionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RoommateResponseModel addRoommate(RoommateRequestModel roommateRequestModel) {

        RoommateAd roommateAd = new RoommateAd(roommateRequestModel);
        User user = userRepository.getById(AuthUtil.getCurrentUserId());

        Optional<Region> region=regionRepository.findById(roommateRequestModel.getRegion());
        if(region.isPresent())
            roommateAd.setRegion(region.get());

        roommateAd.setUser(user);
        return new RoommateResponseModel(roomMateRepository.save(roommateAd));
    }

    @Override
    public List<RoommateResponseModel> getRoommates(Long regionId,int page) {

        Pageable pageable = PageRequest.of(page, 5, Sort.by("date").descending());
        List<RoommateAd> roommateAds=new ArrayList<>();

        
        if(regionId.intValue()!=0)
           roommateAds=roomMateRepository.getRoommatesByRegion(regionId,pageable);
         else
           roommateAds=roomMateRepository.findAll(pageable).getContent();

        return roommateAds
                .stream()
                .map(roommateAd -> new RoommateResponseModel(roommateAd))
                .collect(Collectors.toList());
    }
}
