package org.example.service;

import org.example.model.Vote;

import java.util.List;

public interface VoteService {

    Vote createVote(String userId, Integer petitionId);

    List<Vote> getVotesByPetitionId(Integer petitionId);
    List<Vote> getAllVotes();
}
