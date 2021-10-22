package az.edadi.back.constants;

public enum PhotoEnum {
    USER_DEFAULT_PHOTO("default"),
    ARTICLE_DEFAULT_PHOTO("default"),

    USER_IMAGE_FOLDER("user"),
    BLOG_IMAGE_FOLDER("blog"),
    SUBJECT_FİLEs_FOLDER("file"),

    DOMAIN("http://edadi.az"),
    ROOT_PHOTO_URL("https://edadiaz.s3.eu-central-1.amazonaws.com/public/"),

    IMAGE_SIZE_S("thumbS"),
    IMAGE_SIZE_M("thumbM"),
    IMAGE_SIZE_L("");


    private final String name;


    PhotoEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



    }
