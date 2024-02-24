package az.edadi.back.constants.type;

public enum FileType {
    PDF("pdf"),
    IMAGE("image");
    private String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
