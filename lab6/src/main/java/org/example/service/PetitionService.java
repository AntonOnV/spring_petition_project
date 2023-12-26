package org.example.service;

import org.example.model.Petition;

import java.util.List;

public interface PetitionService {

    Petition createPetition(String name, String description);

    Petition updatePetition(Long id, String name, String description);

    Petition addVote(Long petitionId);

    List<Petition> getPetitions();

    Petition getPetition(Long id);

    void deletePetition(Long id);
}
