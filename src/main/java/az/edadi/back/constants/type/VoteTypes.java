package az.edadi.back.constants.type;

public enum VoteTypes {

    POST("post"),
    COMMENT("comment");

    private final String type;

    VoteTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
