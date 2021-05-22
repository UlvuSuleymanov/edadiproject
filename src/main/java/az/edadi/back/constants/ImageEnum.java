package az.edadi.back.constants;

public enum ImageEnum {
    DEFAULT_IMAGE_NAME("default"),
    ROOT_IMAGE_URL("https://edadi.s3.eu-central-1.amazonaws.com/public/");

     private final String name;
    ImageEnum(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
