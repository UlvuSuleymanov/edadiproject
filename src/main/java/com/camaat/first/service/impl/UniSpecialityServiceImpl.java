package com.camaat.first.service.impl;

  import com.camaat.first.entity.university.UniSpeciality;
 import com.camaat.first.entity.university.University;
 import com.camaat.first.model.response.SpecialitySummaryResModel;
 import com.camaat.first.repository.UniSpecialityRepository;
 import com.camaat.first.repository.UniversityRepository;
 import com.camaat.first.service.UniSpecialityService;
import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Optional;
  import java.util.stream.Collectors;

@Service
public class UniSpecialityServiceImpl implements UniSpecialityService{

    private final UniversityRepository universityRepository;
    private final UniSpecialityRepository uniSpecialityRepository;

    public UniSpecialityServiceImpl(UniversityRepository universityRepository, UniSpecialityRepository uniSpecialityRepository) {
        this.universityRepository = universityRepository;
        this.uniSpecialityRepository = uniSpecialityRepository;
    }



    @Override
    public List<SpecialitySummaryResModel> getSpecialitiesOfUni(String abbr) {


        Optional<University> universityOptional = universityRepository.findByAbbr(abbr);
        List<UniSpeciality> specialities= new ArrayList<>();

        List<SpecialitySummaryResModel> specialitySummaryResModelList = new ArrayList<>();

        if(universityOptional.isPresent()) {
            specialities=universityOptional.get().getUniSpecialityList();


        }

        return  specialities.stream()
                .map(uniSpeciality -> new SpecialitySummaryResModel(uniSpeciality))
                .collect(Collectors.toList());




    }







}
