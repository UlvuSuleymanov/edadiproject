package az.edadi.back.service;

import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.model.response.TextBookAdResponseModel;

import java.util.List;

public interface TextbookAdService {

    TextBookAdResponseModel addTextbookAd(TextbookAdRequestModel textbookAdRequestModel);

    List<TextBookAdResponseModel> getTextbookAd();

    void deleteTextbook(Long id);

}
