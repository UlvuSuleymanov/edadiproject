package com.camaat.first.service;


import java.io.File;

public interface S3Service {

       String setPhoto(String keyName, File file);
	   String updatePhoto (String keyName,File file);


}
