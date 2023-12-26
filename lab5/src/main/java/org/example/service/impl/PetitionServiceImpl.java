package org.example.service.impl;

import org.example.exceptions.NotFoundException;
import org.example.model.Petition;
import org.example.model.User;
import org.example.model.Vote;
import org.example.repository.PetitionRepository;
import org.example.repository.impl.PetitionRepositoryImpl;
import org.example.service.PetitionService;
import org.example.service.UserService;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class PetitionServiceImpl implements PetitionService {

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private PetitionRepository petitionRepository;

    public Petition createPetition(String name, String description) {
        Integer id = petitionRepository.createPetition(name, description);
        try {
           return petitionRepository.getPetition(id);
        } catch (NotFoundException exception) {
            throw new RuntimeException("Petition save failed");
        }
    }

    public Petition updatePetition(Integer id, String name, String description) {
        Petition petition = new Petition();
        petition.setId(id);
        petition.setName(name);
        petition.setDescription(description);
        if (petitionRepository.updatePetition(petition)){
            return getPetition(id);
        } else {
            throw new NotFoundException(String.format("Petition with id %s not found", id));
        }
    }

    @Transactional
    public Petition addVote(Integer petitionId) {

        User user = userService.createUser();
        voteService.createVote(user.getId(), petitionId);

       return getPetition(petitionId);
    }

    public List<Petition> getPetitions() {
        List<Petition> petitions = petitionRepository.getPetitionList();

        if (!CollectionUtils.isEmpty(petitions)) {
            List<Vote> votes = voteService.getAllVotes();
            petitions.forEach(petition -> petition.setVotes(votes.stream()
                    .filter(vote-> vote.getPetitionId().equals(petition.getId()))
                    .toList()));
        }

        return petitions;
    }

    public Petition getPetition(Integer id) {
        Petition petition = petitionRepository.getPetition(id);

            List<Vote> votes = voteService.getVotesByPetitionId(id);
            petition.setVotes(votes);
            return petition;
    }

    public void deletePetition(Integer id) {
        petitionRepository.deleteDeletion(id);
    }
}
