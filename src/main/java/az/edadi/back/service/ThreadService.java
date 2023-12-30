package az.edadi.back.service;

import az.edadi.back.model.request.ThreadRequestModel;
import az.edadi.back.model.response.ThreadResponseModel;

import java.util.List;

public interface ThreadService {

    ThreadResponseModel getThread(ThreadRequestModel threadRequestModel);

    List<ThreadResponseModel> getThreads(int page);

    ThreadResponseModel getThread(Long id);
}
