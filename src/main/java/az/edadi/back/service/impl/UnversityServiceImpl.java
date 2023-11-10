package az.edadi.back.service.impl;

import az.edadi.back.entity.university.University;
import az.edadi.back.model.request.UniRequestModel;
import az.edadi.back.model.response.UniResponseModel;
import az.edadi.back.repository.UniversityRepository;
import az.edadi.back.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UnversityServiceImpl implements UniversityService {

    private final UniversityRepository universityRepository;

    @Override
    public University createUni(UniRequestModel uniRequestModel) {
        University university = new University();
        university.setAbbr(uniRequestModel.getAbbrName());
        university.setInfo(uniRequestModel.getInfo());
        university.setNameAz(uniRequestModel.getName());
        return university;
    }

    @Override
    public List<UniResponseModel> getUnisList() {
        List<University> universityList = universityRepository.findAll();
        List<UniResponseModel> uniResponseModels = universityList
                .stream()
                .map(university -> new UniResponseModel(university))
                .collect(Collectors.toList());

        return uniResponseModels;

    }


    @Override
    public UniResponseModel getUni(Long id) {
       University university = universityRepository.findById(id).orElseThrow(
               EntityNotFoundException::new
        );
        return new UniResponseModel(university);
    }
}
