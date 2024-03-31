package az.edadi.back.constants.type;

import lombok.Getter;

@Getter
public enum PostType {

    UNIVERSITY("university"),

    SPECIALITY("speciality"),

    TOPIC("topic");

    private final String type;

    PostType(String type) {
        this.type = type;
    }

}
