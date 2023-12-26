package org.example.service;

import org.example.exceptions.NotFoundException;
import org.example.model.Petition;
import org.example.model.User;
import org.example.model.Vote;
import org.example.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PetitionService {

    @Autowired
    private UserService userService;

    @Autowired
    private PetitionRepository petitionRepository;

    public Petition createPetition(String name, String description) {
        String id = UUID.randomUUID().toString();

        Petition petition = new Petition(id, name, description, new ArrayList<>());
        petitionRepository.createPetition(petition);
        return petition;
    }

    public Petition updatePetition(String id, String name, String description) {
        Optional<Petition> petitionOptional = petitionRepository.getPetition(id);
        if (petitionOptional.isPresent()) {
            Petition foundPetition = petitionOptional.get();
            if (name!=null) {
                foundPetition.setName(name);
            }
            if (description!=null) {
                foundPetition.setDescription(description);
            }
            petitionRepository.updatePetition(foundPetition);
            return foundPetition;
        } else {
            throw new NotFoundException(String.format("Petition with id %s not found", id));
        }
    }

    public Petition addVote(String petitionId) {

        Vote vote = new Vote(UUID.randomUUID().toString(), petitionId, new User(), LocalDateTime.now());
        Optional<Petition> petitionOptional = petitionRepository.getPetition(petitionId);
        if (petitionOptional.isPresent()) {
            Petition foundPetition = petitionOptional.get();
            foundPetition.addVote(vote);
            petitionRepository.updatePetition(foundPetition);
            return foundPetition;
        } else {
            throw new NotFoundException(String.format("Petition with id %s not found", petitionId));
        }
    }

    public List<Petition> getPetitions() {
        return petitionRepository.getPetitionList();
    }

    public Petition getPetition(String id) {
        Optional<Petition> petitionOptional = petitionRepository.getPetition(id);
        if (petitionOptional.isPresent()) {
            return petitionOptional.get();
        }else {
            throw new NotFoundException(String.format("Petition with id %s not found", id));
        }
    }

    public void deletePetition(String id) {
        petitionRepository.deleteDeletion(id);
    }
}
