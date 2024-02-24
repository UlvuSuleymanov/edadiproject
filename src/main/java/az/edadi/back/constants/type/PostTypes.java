package az.edadi.back.constants.type;

public enum  PostTypes {
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    QUESTION("question");

    private final String type;

    PostTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
