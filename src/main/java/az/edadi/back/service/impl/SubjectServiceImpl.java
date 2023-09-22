package az.edadi.back.service.impl;

import az.edadi.back.entity.auth.User;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.entity.university.Subject;
import az.edadi.back.model.request.SubjectRequestModel;
import az.edadi.back.model.response.SubjectSummaryResponseModel;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.SubjectRepository;
import az.edadi.back.repository.UserRepository;
import az.edadi.back.service.SubjectService;
import az.edadi.back.utility.AuthUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SpecialityRepository specialityRepository;
    private final UserRepository userRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SpecialityRepository specialityRepository, UserRepository userRepository) {
        this.subjectRepository = subjectRepository;
        this.specialityRepository = specialityRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<SubjectSummaryResponseModel> getSubjects(Long specialityId) {
       Optional<Speciality> specialityOptional= specialityRepository.findById(specialityId);
       if(!specialityOptional.isPresent())
        return Collections.emptyList();

    return specialityOptional.get().getSubjects().stream()
              .map(subject -> new SubjectSummaryResponseModel(subject))
              .collect(Collectors.toList());
    }

    @Override
    public Subject addSubject(SubjectRequestModel subjectRequestModel) {
     Long id = AuthUtil.getCurrentUserId();
     Optional<User> user = userRepository.findById(id);
     Optional<Speciality> speciality= specialityRepository.findById(subjectRequestModel.getSpecialityId());

     if(user.isPresent()
             && speciality.isPresent()
             && user.get().getSpeciality().getId().equals(subjectRequestModel.getSpecialityId())){

     Subject subject = new Subject();
     subject.setName(subjectRequestModel.getName());
     subject.setSpeciality(speciality.get());
     subject.setUser(user.get());
      return subjectRepository.save(subject);
    }
     return new Subject();
    }
}
