package az.edadi.back.controller;

import az.edadi.back.model.SummaryModel;
import az.edadi.back.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/region")
public class RegionController {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @GetMapping
    ResponseEntity getRegions(){
        List<SummaryModel> regions = regionRepository.findAll()
                .stream()
                .map(region -> new SummaryModel(region.getId(),region.getName()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(regions);
    }
}
