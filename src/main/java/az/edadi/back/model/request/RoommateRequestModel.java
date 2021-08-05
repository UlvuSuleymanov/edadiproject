package az.edadi.back.model.request;

import lombok.Data;

@Data
public class RoommateRequestModel {
    private String title;
    private String info;
    private Integer amount;
    private String number;
    private Long roomSize;
    private Long region;
}
