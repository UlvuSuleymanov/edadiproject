package az.edadi.back.service.impl;

import az.edadi.back.model.response.RegionRes;
import az.edadi.back.repository.RegionRepository;
import az.edadi.back.service.RegionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionRes> getAllRegions() {
        return regionRepository.findAll()
                .stream()
                .map(
                        region -> new RegionRes(region.getId(), region.getName())
                )
                .toList();
    }
}
