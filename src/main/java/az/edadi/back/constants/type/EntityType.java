package az.edadi.back.constants.type;

import az.edadi.back.exception.model.not_found.EntityTypeNotFoundException;

public enum EntityType {
    USER("user"),
    ROOMMATE("roommate"),
    POST("post"),
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    TOPIC("question"),

    All("all");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EntityType of(String entityTypeString) {
        for (EntityType entityType : EntityType.values())
            if (entityType.type.equalsIgnoreCase(entityTypeString))
                return entityType;
        throw new EntityTypeNotFoundException();
    }
}
