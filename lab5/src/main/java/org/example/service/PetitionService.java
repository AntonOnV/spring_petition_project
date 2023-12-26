package org.example.service;

import org.example.model.Petition;

import java.util.List;

public interface PetitionService {

    Petition createPetition(String name, String description);

    Petition updatePetition(Integer id, String name, String description);

    Petition addVote(Integer petitionId);

    List<Petition> getPetitions();

    Petition getPetition(Integer id);

    void deletePetition(Integer id);
}
