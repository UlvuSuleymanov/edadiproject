package az.edadi.back.controller;

import az.edadi.back.model.request.VoteRequestModel;
import az.edadi.back.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteController {

    private final VoteService voteService;

    @PostMapping("api/vote")
    ResponseEntity addVote(@RequestBody VoteRequestModel voteRequestModel) {
        voteService.addVote(voteRequestModel);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @DeleteMapping("api/vote")
    ResponseEntity deleteVote(@RequestParam Long id, @RequestParam String type) {
        voteService.deleteVote(new VoteRequestModel(type, id));
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }


}
