package az.edadi.back.model;

import az.edadi.back.constants.type.UploadingType;
import az.edadi.back.entity.app.FileItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadyFile {
    private MultipartFile multipartFile;
    private FileItem fileItem;
    private UploadingType uploadingType;
}
