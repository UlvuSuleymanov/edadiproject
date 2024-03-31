package az.edadi.back.constants.type;

import lombok.Getter;

@Getter
public enum FileType {
    PDF("pdf"),
    IMAGE("image");
    private String type;
    FileType(String type) {
        this.type = type;
    }
}
