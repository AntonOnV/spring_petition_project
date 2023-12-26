package org.example.service.impl;

import jakarta.persistence.EntityManager;
import org.example.model.Petition;
import org.example.model.User;
import org.example.model.Vote;
import org.example.repository.VoteRepository;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote createVote(User user, Petition petition) {
        Vote vote = new Vote();
        vote.setVotedAt(LocalDateTime.now());
        vote.setUser(user);
        vote.setPetition(petition);
        return voteRepository.save(vote);
    }

    @Override
    public List<Vote> getVotesByUser(User user) {
        return voteRepository.findByUserId(user.getId());
    }

    @Override
    public Vote findByUserIdAndPetitionId(Long userId, Long petitionId) {
        return voteRepository.findByUserIdAndPetitionId(userId, petitionId);
    }
}
