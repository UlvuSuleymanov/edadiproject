package az.edadi.back.constants;

public enum  PostTypes {
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    TOPIC("topic");

    private final String type;

    PostTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
