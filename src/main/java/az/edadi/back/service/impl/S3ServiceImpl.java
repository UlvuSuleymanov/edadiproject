package az.edadi.back.service.impl;

import az.edadi.back.service.FileService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class S3ServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

    @Autowired
    private final AmazonS3 s3client;

    @Value("${jsa.s3.bucket}")
    private String bucketName;


    public S3ServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }


    @Override
    public String saveProfilePhoto(String keyName, File file,String folder) {
        s3client.putObject(new PutObjectRequest(bucketName + folder, keyName, file));
        return keyName;
//		} catch (AmazonServiceException ase) {
//			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
//			logger.info("Error Message:    " + ase.getMessage());
//			logger.info("HTTP Status Code: " + ase.getStatusCode());
//			logger.info("AWS Error Code:   " + ase.getErrorCode());
//			logger.info("Error Type:       " + ase.getErrorType());
//			logger.info("Request ID:       " + ase.getRequestId());
//        } catch (AmazonClientException ace) {
//            logger.info("Caught an AmazonClientException: ");
//            logger.info("Error Message: " + ace.getMessage());}
//
//     return null;
    }


    @Override
    public String update(String key) {
        return null;
    }

    @Override
    public void deleteProfileImage(String key) {
        s3client.deleteObject(bucketName + "/user", key);
    }


    //
//	@Override
//	public String updatePhoto(String keyName, File file) {
//		PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file);
// 		s3client.putObject(request);
//
//		return keyName;
//	}
    @Override
    public File convertMultiPartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(multipartFile.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }


}
