package az.edadi.back.constants.type;

public enum NotificationType {
    MESSAGE("mesage"),
    LIKE("like"),
    COMMENT("comment"),
    NEW_POST("new_post");

    private String type;

    NotificationType(String type){
        this.type=type;
    }
    public String getType() {
        return type;
    }

}
