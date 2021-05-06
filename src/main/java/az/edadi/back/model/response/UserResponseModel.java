package az.edadi.back.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {
    private String username;
    private String name;
    private String imageUrl;
    private int postCount;
    private int commentCount;

}

