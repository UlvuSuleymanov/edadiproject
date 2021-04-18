package com.camaat.first.service.impl;

 import com.camaat.first.entity.university.University;
import com.camaat.first.model.request.UniRequestModel;
 import com.camaat.first.model.response.UniResponseModel;
 import com.camaat.first.model.response.UniSummaryModel;
 import com.camaat.first.repository.SpecialityRepository;
import com.camaat.first.repository.UniversityRepository;
import com.camaat.first.service.SpecialityService;
import com.camaat.first.service.UniversityService;
 import com.camaat.first.utility.ImageUtil;
 import org.springframework.stereotype.Service;

 import java.util.List;
 import java.util.Optional;
 import java.util.stream.Collectors;
@Service
public class UnversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final SpecialityRepository specialityRepository;
    private final SpecialityService specialityService;

    public UnversityServiceImpl(UniversityRepository universityRepository, SpecialityRepository specialityRepository, SpecialityService specialityService) {
        this.universityRepository = universityRepository;
        this.specialityRepository = specialityRepository;
        this.specialityService = specialityService;
    }

    @Override
    public University createUni(UniRequestModel uniRequestModel) {
         University university = new University();
         university.setAbbr(uniRequestModel.getAbbrName());
         university.setInfo(uniRequestModel.getInfo());
         university.setNameAz(uniRequestModel.getName());
         return university;
    }

    @Override
    public List<UniSummaryModel> getUnisList() {
        List<University> universityList = universityRepository.findAll();
        List<UniSummaryModel> uniResponseModels=universityList
                .stream()
                .map(university -> getUniSummaryResponseModel(university))
                .collect(Collectors.toList());

     return uniResponseModels;

    }


    @Override
    public UniSummaryModel getUniSummaryResponseModel(University university) {
        UniSummaryModel uniSummaryModel = new UniSummaryModel();
       return   uniSummaryModel.setAbbr(university.getAbbr())
                .setId(university.getId())
                .setAbbr(university.getAbbr())
                .setUrl(ImageUtil.getPhotoUrl(university.getAbbr()) )
                .setName(university.getNameAz());
    }




    @Override
    public boolean existsById(Long id) {
      return   universityRepository.existsById(id);
    }

    @Override
    public UniResponseModel getUni(String abbrName)
    {
        Optional<University> university = universityRepository.findByAbbr(abbrName);

        if(university.isPresent())
        return new UniResponseModel(university.get());


       return null;

    }
}
