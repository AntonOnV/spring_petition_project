package org.example.repository;

import org.example.model.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepository {

    Vote saveVote(Vote vote);

    Optional<Vote> getVoteById(String voteId);

    List<Vote> getVotesByPetitionId(Integer petitionId);

    List<Vote> getAll();
}
