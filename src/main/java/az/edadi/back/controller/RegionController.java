package az.edadi.back.controller;

import az.edadi.back.model.SummaryModel;
import az.edadi.back.model.response.RegionRes;
import az.edadi.back.service.RegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/region")
public class RegionController {

    private final RegionService  regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    ResponseEntity<List<RegionRes>> getRegions(){
    return ResponseEntity.ok(regionService.getAllRegions());
    }
}
