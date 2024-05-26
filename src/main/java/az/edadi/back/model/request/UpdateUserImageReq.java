package az.edadi.back.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UpdateUserImageReq {
    private UUID id;
    private String url;
}
