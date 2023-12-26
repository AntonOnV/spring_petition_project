package org.example.service.impl;

import org.example.model.Vote;
import org.example.repository.VoteRepository;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote createVote(String userId, Integer petitionId) {
        Vote vote = new Vote();
        vote.setVotedAt(LocalDateTime.now());
        vote.setUserId(userId);
        vote.setPetitionId(petitionId);
        vote.setId(UUID.randomUUID().toString());
        voteRepository.saveVote(vote);

        return vote;
    }

    @Override
    public List<Vote> getVotesByPetitionId(Integer petitionId) {
        return voteRepository.getVotesByPetitionId(petitionId);
    }

    @Override
    public List<Vote> getAllVotes() {
        return voteRepository.getAll();
    }
}
