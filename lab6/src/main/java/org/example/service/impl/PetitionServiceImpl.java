package org.example.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.config.CurrentUser;
import org.example.exceptions.NotFoundException;
import org.example.model.Petition;
import org.example.model.User;
import org.example.repository.PetitionRepository;
import org.example.service.PetitionService;
import org.example.service.UserService;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PetitionServiceImpl implements PetitionService {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private PetitionRepository petitionRepository;

    @Autowired
    private EntityManager entityManager;

    public Petition createPetition(String name, String description) {
        Petition petition = new Petition();
        petition.setName(name);
        petition.setDescription(description);
        return petitionRepository.save(petition);
    }

    public Petition updatePetition(Long id, String name, String description) {

        Petition petition = petitionRepository.getPetition(id);
        petition.setName(name);
        petition.setDescription(description);
        return petitionRepository.save(petition);
    }

    @Transactional
    public Petition addVote(Long petitionId) {
        User user = currentUser.getUser();
        if(user!=null){
            Petition petition = petitionRepository.getPetition(petitionId);
            if(voteService.findByUserIdAndPetitionId(user.getId(), petitionId) != null){
                throw new IllegalStateException("User already voted");
            }
            voteService.createVote(user, petition);
            return getPetition(petitionId);
        }

        throw new IllegalStateException("User not logged in");
    }

    public List<Petition> getPetitions() {
        TypedQuery<Petition> query =
                entityManager.createNamedQuery("Petition.findAll", Petition.class);
        return query.getResultList();
    }

    public Petition getPetition(Long id) {
        Petition petition = petitionRepository.getPetition(id);

        if (petition == null) {
            throw new NotFoundException(String.format("Petition with id %s not found", id));
        }
        return petition;
    }

    public void deletePetition(Long id) {
        petitionRepository.deleteById(id);
    }
}
