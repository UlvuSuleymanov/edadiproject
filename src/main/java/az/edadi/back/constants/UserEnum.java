package az.edadi.back.constants;

public enum     UserEnum {

    DEFAULT_IMAGE_NAME("default");


    private  final  String imageName;

    UserEnum(String imageName) {
        this.imageName=imageName;
    }

    public String getImageName() {
        return imageName;
    }
}
