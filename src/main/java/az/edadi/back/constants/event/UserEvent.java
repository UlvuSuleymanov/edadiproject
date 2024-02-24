package az.edadi.back.constants.event;

public enum UserEvent {
    ADD_POST("add-post",10,"ADD_POST_LIMIT_ERROR"),
    ADD_COMMENT("add-comment",40,"ADD_COMMENT_LIMIT_ERROR"),
    ADD_VOTE("add-vote",100,"ADD_VOTE_LIMIT_ERROR"),
    ADD_TOPIC("add-topic",5,"ADD_TOPIC_LIMIT_ERROR"),
    ADD_THREAD("add-thread",7,"ADD_THREAD_LIMIT_ERROR"),
    ADD_ROOMMATE("add-roommate",2,"ADD_ROOMMATE_LIMIT_ERROR"),
    ADD_TEXTBOOKAD("add-textbookad",10,"ADD_TEXTBOOK_LIMIT_ERROR"),
    ADD_ARTICLE("add-article",5,"ADD_ARTICLE_LIMIT_ERROR");

    private final String event;
    private final int limit;

    private String errorMessage;

    UserEvent(String event,int limit,String errorMessage ) {
        this.event = event;
        this.limit=limit;
        this.errorMessage=errorMessage;
    }

    public String getEvent() {
        return event;
    }
    public int getLimit() {
        return limit;
    }
    public String getErrorMessage(){
        return errorMessage;
    }

}
