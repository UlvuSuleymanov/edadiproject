package az.edadi.back.service.impl;

import az.edadi.back.constants.UserAuthority;
import az.edadi.back.entity.textbook.TextBookType;
import az.edadi.back.entity.textbook.TextbookAd;
import az.edadi.back.entity.university.Speciality;
import az.edadi.back.exception.model.UserAuthorizationException;
import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.model.request.TextbookAdRequestParamsModel;
import az.edadi.back.model.response.TextBookAdResponseModel;
import az.edadi.back.repository.SpecialityRepository;
import az.edadi.back.repository.TextbookAdRepository;
import az.edadi.back.repository.TextbookTypeRepository;
import az.edadi.back.service.TextbookAdService;
import az.edadi.back.utility.AuthUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TextbookAdServiceImpl implements TextbookAdService {

    private final TextbookAdRepository textbookAdRepository;
    private final TextbookTypeRepository textbookTypeRepository;
    private final SpecialityRepository specialityRepository;

    @Override
    public TextbookAd addTextbookAd(TextbookAdRequestModel textbookAdRequestModel) {
        TextbookAd textbookAd = TextbookAd.from(textbookAdRequestModel);

        TextBookType textBookType = textbookTypeRepository
                .findById(textbookAdRequestModel
                        .getType()).
                orElseThrow(
                        () -> new EntityNotFoundException()
                );
        textbookAd.setType(textBookType);


        Speciality speciality = specialityRepository
                .findById(textbookAdRequestModel
                        .getSpecialityId()).
                orElseThrow(
                        () -> new EntityNotFoundException()
                );
        textbookAd.setSpeciality(speciality);

        return textbookAdRepository.save(textbookAd);
    }

    @Override
    public List<TextBookAdResponseModel> getTextbookAdsList(TextbookAdRequestParamsModel textbookAdRequestParamsModel) {

        Pageable pageable = textbookAdRequestParamsModel.isAsc() ?
                PageRequest.of(textbookAdRequestParamsModel.getPage(), 10, Sort.by("date").ascending()) :
                PageRequest.of(textbookAdRequestParamsModel.getPage(), 10, Sort.by("date").descending());

        List<TextbookAd> textbookAdList = textbookAdRepository
                .getTextbooks(textbookAdRequestParamsModel.getType(),
                        textbookAdRequestParamsModel.getSpecialityId(),
                        pageable
            );

        return textbookAdList.stream()
                .map(textbookAd -> new TextBookAdResponseModel(textbookAd))
                .collect(Collectors.toList());

    }

    @Override
    public TextBookAdResponseModel getTextbookAd(Long id) {
        TextbookAd textbookAd = textbookAdRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        return new TextBookAdResponseModel(textbookAd);
    }


    @Override
    public void deleteTextbook(Long id) {

        log.info("User {} try delete TextbookAd with id {} ", AuthUtil.getCurrentUsername(), id);
        TextbookAd textbookAd = textbookAdRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException()
        );
        if (!textbookAd.getUser().getId().equals(AuthUtil.getCurrentUserId()) &&
                !AuthUtil.hasAuthority(UserAuthority.ADMIN_UPDATE))
            throw new UserAuthorizationException();

        textbookAdRepository.delete(textbookAd);

    }
}
