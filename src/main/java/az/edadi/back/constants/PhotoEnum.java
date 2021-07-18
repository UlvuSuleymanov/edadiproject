package az.edadi.back.constants;

public enum PhotoEnum {
    USER_DEFAULT_PHOTO("default"),
    ARTICLE_DEFAULT_PHOTO("default"),
    ROOT_PHOTO_URL("https://edadi.s3.eu-central-1.amazonaws.com/public/");

     private final String name;


    PhotoEnum(String name) {
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
