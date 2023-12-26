package org.example.repository;

import org.example.model.Petition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PetitionRepository extends CrudRepository<Petition, Long> {

    @Query("SELECT p FROM Petition p WHERE id = :id")
    Petition getPetition(Long id);
}
