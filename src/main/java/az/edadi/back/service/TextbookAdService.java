package az.edadi.back.service;

import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.model.request.TextbookAdRequestParamsModel;
import az.edadi.back.model.response.TextBookAdResponseModel;

import java.util.List;

public interface TextbookAdService {

    void addTextbookAd(TextbookAdRequestModel textbookAdRequestModel);

    List<TextBookAdResponseModel> getTextbookAd(TextbookAdRequestParamsModel textbookAdRequestParamsModel);

    TextBookAdResponseModel getTextbookAd(Long id);

    void deleteTextbook(Long id);

}
