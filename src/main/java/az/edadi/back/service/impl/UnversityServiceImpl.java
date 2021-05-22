package az.edadi.back.service.impl;

 import az.edadi.back.entity.university.University;
 import az.edadi.back.model.request.UniRequestModel;
 import az.edadi.back.model.response.UniResponseModel;
 import az.edadi.back.model.response.UniSummaryModel;
 import az.edadi.back.repository.SpecialityRepository;
 import az.edadi.back.repository.UniversityRepository;
 import az.edadi.back.service.SpecialityService;
 import az.edadi.back.service.UniversityService;
 import az.edadi.back.utility.ImageUtil;
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
    public List<UniResponseModel> getUnisList() {
        List<University> universityList = universityRepository.findAll();
        List<UniResponseModel> uniResponseModels=universityList
                .stream()
                .map(university -> new UniResponseModel(university))
                .collect(Collectors.toList());

     return uniResponseModels;

    }


//    @Override
//    public UniSummaryModel getUniSummaryResponseModel(University university) {
//        UniSummaryModel uniSummaryModel = new UniSummaryModel();
//       return   uniSummaryModel.setAbbr(university.getAbbr())
//                .setId(university.getId())
//                .setAbbr(university.getAbbr())
//                .setUrl(ImageUtil.getPhotoUrl(university.getAbbr()) )
//                .setName(university.getNameAz());
//    }




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
