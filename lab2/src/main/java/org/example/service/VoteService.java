package org.example.service;

import org.example.model.User;
import org.example.model.Vote;
import org.example.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote createVote(String petitionId, User user) {
        Vote vote = new Vote(UUID.randomUUID().toString(), petitionId, user, LocalDateTime.now());

        return vote;
    }
}
