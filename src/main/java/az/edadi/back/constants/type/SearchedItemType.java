package az.edadi.back.constants.type;

import az.edadi.back.exception.model.not_found.NoSuchFileTypeException;

public enum SearchedItemType {
    USER("user"),
    ROOMMATE("roommate"),
    POST("post"),
    UNIVERSITY("university"),
    SPECIALITY("speciality"),
    TOPIC("topic");

    private final String type;

    public static SearchedItemType of(String searchType) {
        for (SearchedItemType searchedItemType : SearchedItemType.values())
            if (searchedItemType.type.equalsIgnoreCase(searchType))
                return searchedItemType;

        throw new NoSuchFileTypeException();
    }

    SearchedItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
