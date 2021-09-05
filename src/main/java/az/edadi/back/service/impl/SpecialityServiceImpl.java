package az.edadi.back.service.impl;

  import az.edadi.back.service.SpecialityService;
  import az.edadi.back.entity.university.Speciality;
  import az.edadi.back.entity.university.University;
   import az.edadi.back.model.response.SpecialitySummaryResModel;
  import az.edadi.back.repository.SpecialityRepository;
  import az.edadi.back.repository.UniversityRepository;
  import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("speciality")
    public List<SpecialitySummaryResModel> getSpecialities(Long group) {

        List<Speciality> specialities= new ArrayList<>();

        specialities=specialityRepository.getSpeciality(group);



        return  specialities.stream()
                .map(speciality -> new SpecialitySummaryResModel(speciality))
                .collect(Collectors.toList());


    }

    @Override
    public List<SpecialitySummaryResModel> getUniversitySpecialities(Long uniId, Long group) {

        List<Speciality> specialities= specialityRepository.getUniSpecialities(uniId,group);

        return  specialities.stream()
                .map(speciality -> new SpecialitySummaryResModel(speciality))
                .collect(Collectors.toList());
    }


}
