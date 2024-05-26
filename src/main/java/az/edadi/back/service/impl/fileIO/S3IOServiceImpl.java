package az.edadi.back.service.impl.fileIO;

import az.edadi.back.config.S3Bean;
import az.edadi.back.model.ReadyFile;
import az.edadi.back.model.response.UploadFileRes;
import az.edadi.back.service.FileIOService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@AllArgsConstructor
public class S3IOServiceImpl implements FileIOService {

    private final AmazonS3 s3client;
    private final S3Bean s3Bean;
    private final String PUBLIC_ACCESSED_FOLDER="public/";
    @Override
    public UploadFileRes uploadUserImage(MultipartFile type, File content, String uuid) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(content.length());
        metadata.setContentType(type.getContentType());
        String key= PUBLIC_ACCESSED_FOLDER+"user/"+uuid;
        PutObjectRequest request = new PutObjectRequest(s3Bean.getBucket(), key, content);
        request.setMetadata(metadata);
        s3client.putObject(request);
        return new UploadFileRes(uuid,s3client.getUrl(s3Bean.getBucket(), key).toString());
    }

    @Override
    public String saveFile(ReadyFile file)  {
        return "asdas";
    }

    @Override
    public String update(String key,File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {
     }


}
