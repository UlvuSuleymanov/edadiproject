package az.edadi.back.service.impl.fileIO;

import az.edadi.back.service.FileIOService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Profile("file-s3")
public class S3IOServiceImpl implements FileIOService {

    @Autowired
    private final AmazonS3 s3client;

    @Value("${jsa.s3.bucket}")
    private String bucketName;

    public S3IOServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Override
    public String saveFile(String keyName, File file, String folder) {
        s3client.putObject(new PutObjectRequest(bucketName,folder+keyName, file));
        return keyName;
    }


    @Override
    public String update(String key,File file) {
        return null;
    }

    @Override
    public void deleteFile(String key, String folder) {
        s3client.deleteObject(bucketName + folder, key);
    }


}
