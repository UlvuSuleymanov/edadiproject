package az.edadi.back.constants;

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
