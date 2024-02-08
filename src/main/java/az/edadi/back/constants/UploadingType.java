package az.edadi.back.constants;

import az.edadi.back.exception.model.NoSuchFileTypeException;

public enum UploadingType {
    USER("user", "public/user/",FileType.IMAGE, "SM"),
    ROOMMATE("roommate", "public/roommate/", FileType.IMAGE, "M"),
    ARTICLE("article","public/article/",FileType.IMAGE,"M"),
    NOTE("note", "public/note/", FileType.PDF,"");


    private String parent;
    private String folder;
    private FileType fileType;
    private String sizes;

    UploadingType(String parent, String folder, FileType fileType, String sizes) {
        this.parent = parent;
        this.folder = folder;
        this.fileType=fileType;
        this.sizes = sizes;
    }

    public static UploadingType of(String parent) {
        for (UploadingType ft : UploadingType.values()) {
            if (ft.parent.equalsIgnoreCase(parent)) {
                return ft;
            }
        }
        throw new NoSuchFileTypeException();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }
}
