package az.edadi.back.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFileRes {
    private String id;
    private String url;
}
