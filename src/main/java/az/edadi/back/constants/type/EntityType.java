package az.edadi.back.constants.type;

import az.edadi.back.exception.model.not_found.EntityTypeNotFoundException;

public enum EntityType {
    USER("user","/user"),
    ROOMMATE("roommate","/roommate"),
    POST("post","/post"),
    UNIVERSITY("university","/university"),
    SPECIALITY("speciality","/speciality"),
    CONVERSATION("conversation","/conversation"),
    TOPIC("topic","/topic"),
    FILE("file","/file"),
    All("all","/");

    private final String type;
    private final String page;

    EntityType(String type,String page) {
        this.type = type;
        this.page=page;
    }
    public String getType() {
        return type;
    }
    public String getPage() {
        return page;
    }
    public static EntityType of(String entityTypeString) {
        for (EntityType entityType : EntityType.values())
            if (entityType.type.equalsIgnoreCase(entityTypeString))
                return entityType;
        throw new EntityTypeNotFoundException();
    }
}
