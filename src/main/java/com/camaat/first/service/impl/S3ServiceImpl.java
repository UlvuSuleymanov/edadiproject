package com.camaat.first.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
 import com.amazonaws.services.s3.model.PutObjectRequest;

import com.camaat.first.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class S3ServiceImpl implements S3Service {
	
	private Logger logger = LoggerFactory.getLogger(S3ServiceImpl.class);

	@Autowired
	private final AmazonS3 s3client;

	@Value("${jsa.s3.bucket}")
	private String bucketName;


    public S3ServiceImpl(AmazonS3 s3client) {
        this.s3client = s3client;
    }


    @Override
	public String setPhoto(String keyName, File file) {

		try {


  			s3client.putObject(new PutObjectRequest(bucketName, keyName, file));

			return keyName;

		} catch (AmazonServiceException ase) {
			logger.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
			logger.info("Error Message:    " + ase.getMessage());
			logger.info("HTTP Status Code: " + ase.getStatusCode());
			logger.info("AWS Error Code:   " + ase.getErrorCode());
			logger.info("Error Type:       " + ase.getErrorType());
			logger.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.info("Caught an AmazonClientException: ");
            logger.info("Error Message: " + ace.getMessage());}

     return null;
	}

//
//	@Override
//	public String updatePhoto(String keyName, File file) {
//		PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file);
// 		s3client.putObject(request);
//
//		return keyName;
//	}



}
