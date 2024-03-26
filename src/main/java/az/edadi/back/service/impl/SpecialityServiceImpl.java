package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.model.response.SpecialityResponseModel;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.SpecialityService;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.University;
import az.edadi.back.model.response.SpecialitySummaryResModel;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.utility.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
    private final UniversityRepository universityRepository;
    private final SpecialityRepository specialityRepository;
    private final UserRepository userRepository;

    public SpecialityServiceImpl(UniversityRepository universityRepository, SpecialityRepository specialityRepository, UserRepository userRepository) {
        this.universityRepository = universityRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Cacheable("specialities")
    public List<SpecialitySummaryResModel> getSpecialityList(Long group) {
        return specialityRepository.findAllByGroup(group).stream()
                .map(SpecialitySummaryResModel::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<SpecialitySummaryResModel> getUniversitySpecialities(Long uniId) {
        Optional<University> university = universityRepository.findById(uniId);
        if (university.isPresent())
            return university.get().getSpecialities().stream()
                    .map(speciality -> new SpecialitySummaryResModel(speciality))
                    .collect(Collectors.toList());

        return Collections.emptyList();
    }


    @Override
    public List<SpecialitySummaryResModel> getUniversitySpecialitiesWithGroup(Long uniId, Long group) {
        if (group == 0)
            return getUniversitySpecialities(uniId);

        List<Speciality> specialities = specialityRepository.getUniSpecialities(uniId, group);
        return specialities.stream()
                .map(speciality -> new SpecialitySummaryResModel(speciality))
                .collect(Collectors.toList());
    }

    @Override
    public SpecialityResponseModel getSpeciality(Long code) {
        Optional<Speciality> speciality = specialityRepository.findBySpecialityCode(code);

        if (!speciality.isPresent())
            throw new EntityNotFoundException();

        SpecialityResponseModel responseModel = new SpecialityResponseModel(speciality.get());

        if (AuthUtil.userIsAuthenticated()) {
            Optional<User> user = userRepository.findById(AuthUtil.getCurrentUserId());
            if (user.isPresent() && user.get().getSpeciality() != null && user.get().getSpeciality().getId().longValue() == speciality.get().getId().longValue()) {
                responseModel.setCanAddSubject(true);
            }

        }


        return responseModel;
    }


}
