package az.edadi.back.model.request;

 import lombok.Data;
import org.hibernate.validator.constraints.Length;

 import java.util.ArrayList;
 import java.util.List;


@Data
public class RoommateReq {


    @Length(min = 1,max = 255)
    private String haveHouse;

    private String houseInfo;

    private List<String> urls = new ArrayList<>();

    @Length(min = 1,max = 10)
    private String sex;

    private String contact;

    @Length(min = 1,max = 10)
    private Long regionId;

    @Length(min = 1,max = 255)
    private String generalInfo;



}
