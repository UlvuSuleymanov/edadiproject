package com.camaat.first.utility;

public enum  UserEnum {
    DEFAULT_USER_IMAGE_NAME("default");


    private  final  String imageName;

    UserEnum(String imageName) {
        this.imageName=imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
