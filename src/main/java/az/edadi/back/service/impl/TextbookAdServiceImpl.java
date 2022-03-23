package az.edadi.back.service.impl;

import az.edadi.back.model.request.TextbookAdRequestModel;
import az.edadi.back.model.response.TextBookAdResponseModel;
import az.edadi.back.service.TextbookAdService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TextbookAdServiceImpl implements TextbookAdService {
    @Override
    public TextBookAdResponseModel addTextbookAd(TextbookAdRequestModel textbookAdRequestModel) {
        return null;
    }

    @Override
    public List<TextBookAdResponseModel> getTextbookAd() {
        return null;
    }

    @Override
    public void deleteTextbook(Long id) {

    }
}
