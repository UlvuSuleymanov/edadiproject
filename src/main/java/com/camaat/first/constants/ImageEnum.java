package com.camaat.first.constants;

public enum ImageEnum {
    DEFAULT_IMAGE_NAME("default"),
    ROOT_IMAGE_URL("https://my-spring-ulvu.s3.us-east-2.amazonaws.com/");

     private final String name;
    ImageEnum(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
