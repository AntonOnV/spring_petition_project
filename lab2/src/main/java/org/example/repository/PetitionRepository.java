package org.example.repository;

import org.example.model.Petition;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PetitionRepository {

    private List<Petition> petitionList = new ArrayList<>();

    public void createPetition(Petition petition) {
        petitionList.add(petition);
    }

    public void updatePetition(Petition petition)  {
        List<Petition> updatedList = petitionList.stream()
                .filter(petition1 -> !petition1.getId().equals(petition.getId()))
                .collect(Collectors.toList());

        updatedList.add(petition);
        petitionList = updatedList;
    }

    public void deleteDeletion(String id) {
        petitionList = petitionList.stream()
                .filter(petition -> !petition.getId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Petition> getPetitionList() {
        return petitionList;
    }

    public Optional<Petition> getPetition(String id) {
        return petitionList.stream().filter(petition -> petition.getId().equals(id))
                .findFirst();
    }
}
