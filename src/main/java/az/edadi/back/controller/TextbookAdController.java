package az.edadi.back.controller;

import az.edadi.back.constants.event.UserEvent;
import az.edadi.back.entity.textbook.TextbookAd;
import az.edadi.back.model.SummaryModel;
import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.model.request.TextbookAdRequestParamsModel;
import az.edadi.back.model.response.TextBookAdResponseModel;
import az.edadi.back.repository.TextbookTypeRepository;
import az.edadi.back.service.TextbookAdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/textbookAd")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TextbookAdController {

    private final TextbookAdService textbookAdService;
    private final TextbookTypeRepository textbookTypeRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    ResponseEntity getTextbookAdList(@ModelAttribute @Valid
                                     TextbookAdRequestParamsModel
                                             textbookAdRequestParamsModel) {
        List<TextBookAdResponseModel> textbookAdsList = textbookAdService
                .getTextbookAdsList(textbookAdRequestParamsModel);
        return ResponseEntity.ok(textbookAdsList);
    }

    @GetMapping("/type")
    ResponseEntity getTypes() {
        return ResponseEntity.ok(
                textbookTypeRepository.findAll()
                        .stream()
                        .map(
                                textBookType -> new SummaryModel(textBookType.getId(), textBookType.getType())
                        ).collect(Collectors.toList())
        );
    }

    @PostMapping
    ResponseEntity addTextbookAd(@RequestBody @Valid TextbookAdRequestModel textbookAdRequestModel) {
        applicationEventPublisher.publishEvent(UserEvent.ADD_TEXTBOOKAD);
        TextbookAd textbookAd = textbookAdService
                .addTextbookAd(textbookAdRequestModel);
        TextBookAdResponseModel textBookAdResponseModel = new TextBookAdResponseModel(textbookAd);

        return ResponseEntity.ok(textBookAdResponseModel);
    }

    @DeleteMapping("/{id}")
    void deleteTextbookAd(@PathVariable Long id) {
        textbookAdService.deleteTextbook(id);
    }

    @GetMapping("/{id}")
    ResponseEntity getTextbookAd(@PathVariable Long id) {
        return ResponseEntity.ok(textbookAdService.getTextbookAd(id));
    }

}
