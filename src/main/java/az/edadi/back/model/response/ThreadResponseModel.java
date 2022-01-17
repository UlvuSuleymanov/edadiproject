package az.edadi.back.model.response;

import az.edadi.back.entity.message.Thread;
import az.edadi.back.model.UserSummary;
import az.edadi.back.utility.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadResponseModel {
    private Long threadId;
    private List<UserSummary> users;

    public ThreadResponseModel(Thread thread) {
        threadId=thread.getId();
        users = thread.getUserThread().stream()
                .filter(userThread -> !userThread.getUser().getId().equals(AuthUtil.getCurrentUserId()))
                .map(userThread -> new UserSummary(userThread.getUser()))
                .collect(Collectors.toList());

    }
}
