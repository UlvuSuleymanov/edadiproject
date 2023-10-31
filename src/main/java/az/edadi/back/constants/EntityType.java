package az.edadi.back.constants;

public enum EntityType {
    USER("user"),
    ROOMMATE("roommate"),
    POST("post"),
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    QUESTION("question");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
