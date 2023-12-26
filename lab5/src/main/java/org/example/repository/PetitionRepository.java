package org.example.repository;

import org.example.model.Petition;

import java.util.List;

public interface PetitionRepository {

    Integer createPetition(String name, String description);

    boolean updatePetition(Petition petition);

    void deleteDeletion(Integer id);

    List<Petition> getPetitionList();

    Petition getPetition(Integer id);


}
