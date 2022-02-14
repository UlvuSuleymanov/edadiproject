package az.edadi.back.service;

import az.edadi.back.model.request.VoteRequestModel;

public interface VoteService {

    void addVote(VoteRequestModel voteRequestModel);

    void deleteVote(VoteRequestModel voteRequestModel);

}
