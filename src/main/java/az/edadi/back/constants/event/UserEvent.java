package az.edadi.back.constants.event;

public enum UserEvent {
    ADD_POST("add-post",10),
    ADD_COMMENT("add-comment",40),
    ADD_VOTE("add-vote",100),
    ADD_QUESTION("add-question",5),
    ADD_THREAD("add-thread",7),
    ADD_ROOMMATE("add-roommate",2),
    ADD_TEXTBOOKAD("add-textbookad",10);

    private final String event;
    private int limit;

    UserEvent(String event,int limit) {
        this.event = event;
        this.limit=limit;
    }

    public String getEvent() {
        return event;
    }
    public int getLimit() {
        return limit;
    }

}
