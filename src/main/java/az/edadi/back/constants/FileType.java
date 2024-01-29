package az.edadi.back.constants;

import az.edadi.back.exception.model.NoSuchFileTypeException;

public enum FileType {
    USER("user", "/public/user", "SM"),
    ROOMMATE("roommate", "/public/roommate", "M"),
    ARTICLE("article","/public/article","M"),
    NOTE("note", "/public/note", "");


    private String parent;
    private String folder;
    private String sizes;

    FileType(String parent, String folder, String sizes) {
        this.parent = parent;
        this.folder = folder;
        this.sizes = sizes;
    }

    public static FileType of(String parent) {
        for (FileType ft : FileType.values()) {
            if (ft.parent.equalsIgnoreCase(parent)) {
                return ft;
            }
        }
        throw new NoSuchFileTypeException();
    }

}
