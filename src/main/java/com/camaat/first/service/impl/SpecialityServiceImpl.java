package com.camaat.first.service.impl;

  import com.camaat.first.entity.university.Speciality;
  import com.camaat.first.entity.university.University;
   import com.camaat.first.model.response.SpecialitySummaryResModel;
  import com.camaat.first.repository.SpecialityRepository;
  import com.camaat.first.repository.UniversityRepository;
 import com.camaat.first.service.SpecialityService;
import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 import java.util.List;
 import java.util.Optional;
  import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private final UniversityRepository universityRepository;
    private final SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(UniversityRepository universityRepository, SpecialityRepository specialityRepository) {
        this.universityRepository = universityRepository;
        this.specialityRepository = specialityRepository;
    }



    @Override
    public List<SpecialitySummaryResModel> getSpecialities(String abbr,Long group) {



        Optional<University> universityOptional = universityRepository.findByAbbr(abbr);

        List<Speciality> specialities= new ArrayList<>();

        List<SpecialitySummaryResModel> specialitySummaryResModelList = new ArrayList<>();

        if(universityOptional.isPresent()) {

             if(group==0)
                specialities=universityOptional.get().getSpecialities();

             else
                specialities=specialityRepository.getSpeciality(universityOptional.get().getId(), group);

        }

        return  specialities.stream()
                .map(speciality -> new SpecialitySummaryResModel(speciality))
                .collect(Collectors.toList());




    }




}
