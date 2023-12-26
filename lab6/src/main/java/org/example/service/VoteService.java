package org.example.service;

import org.example.model.Petition;
import org.example.model.User;
import org.example.model.Vote;

import java.util.List;

public interface VoteService {

    Vote createVote(User user, Petition petition);

    List<Vote> getVotesByUser(User user);

    Vote findByUserIdAndPetitionId(Long userId, Long petitionId);
}
