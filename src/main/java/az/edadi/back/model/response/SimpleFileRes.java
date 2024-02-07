package az.edadi.back.model.response;

import az.edadi.back.entity.app.FileItem;
import lombok.Data;

@Data
public class SimpleFileRes {
    private String id;
    private String url;
    private String name;
    private String key;
    private String folder;
    private String contentType;
    private String parent;
    private String extension;
    private Long size;

    public SimpleFileRes(FileItem fileItem) {
        id=fileItem.getId().toString();
        url="http://localhost:9000/edadi/"+fileItem.getName();
        name=fileItem.getName();
        key=fileItem.getId().toString();
        folder=fileItem.getFolder();
        contentType=fileItem.getContentType();
        parent = fileItem.getParent();
        extension=fileItem.getExtension();
        size=fileItem.getSize();
    }

    public SimpleFileRes() {

    }
}
