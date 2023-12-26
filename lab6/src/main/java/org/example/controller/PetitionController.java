package org.example.controller;

import org.apache.commons.lang3.StringUtils;
import org.example.model.Petition;
import org.example.openapi.api.PetitionsApi;
import org.example.openapi.model.PetitionRequest;
import org.example.openapi.model.PetitionResponse;
import org.example.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PetitionController implements PetitionsApi {

    @Autowired
    private PetitionService petitionService;

    @Override
    public ResponseEntity<PetitionResponse> addVote(Long id) {
        Petition petition = petitionService.addVote(id);
        PetitionResponse petitionResponse = mapToResponse(petition);
        return new ResponseEntity<>(petitionResponse, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<PetitionResponse> createPetition(PetitionRequest petitionRequest) {
        Petition petition = petitionService.createPetition(petitionRequest.getName(), petitionRequest.getDescription());
        PetitionResponse petitionResponse = mapToResponse(petition);
        return new ResponseEntity<>(petitionResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deletePetitionById(Long id) {
        petitionService.deletePetition(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<PetitionResponse>> listPetition(Integer limit, Integer pageNumber, String filter) {
        List<Petition> petitionList = petitionService.getPetitions();
        petitionList = applyFilter(filter, petitionList);
        petitionList = dummyPagination(limit, pageNumber, petitionList);
        List<PetitionResponse> responseList = petitionList.stream()
                .map(PetitionController::mapToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PetitionResponse> getPetitionById(Long id) {
        Petition petition = petitionService.getPetition(id);
        PetitionResponse petitionResponse = mapToResponse(petition);
        return new ResponseEntity<>(petitionResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PetitionResponse> updatePetitionById(Long id, PetitionRequest petitionRequest) {
        Petition updatedPetition = petitionService.updatePetition(id, petitionRequest.getName(), petitionRequest.getDescription());
        PetitionResponse petitionResponse = mapToResponse(updatedPetition);
        return new ResponseEntity<>(petitionResponse, HttpStatus.OK);
    }

    private static PetitionResponse mapToResponse(Petition petition) {
        PetitionResponse petitionResponse = new PetitionResponse();
        petitionResponse.setId(petition.getId());
        petitionResponse.setName(petition.getName());
        petitionResponse.setDescription(petition.getDescription());
        petitionResponse.setVotes(petition.getVotes() == null ? 0 : petition.getVotes().size());
        return petitionResponse;
    }

    private List<Petition> applyFilter(String filter, List<Petition> petitions) {
        if (StringUtils.isBlank(filter)) {
            return petitions;
        }

        List<String> filters = splitFilterString(filter);
        return petitions.stream()
                .filter(petition -> matchesFilters(petition, filters))
                .collect(Collectors.toList());
    }

    private List<String> splitFilterString(String filter) {
        List<String> filters = List.of(filter.split("&"));

        return filters.stream().map(filt -> {
            filt = filt.replace("(", "");
            filt = filt.replace(")", "");
            return filt;
        }).collect(Collectors.toList());
    }

    private boolean matchesFilters(Petition petition, List<String> filters) {
        for (String filter : filters) {
            String[] parts = filter.split(",");
            if (parts.length == 3) {
                String operation = parts[0].trim();
                String field = parts[1].trim();
                String value = parts[2].trim();

                switch (operation) {
                    case "eq" -> {
                        if (!getObjectField(petition, field).equals(value)) {
                            return false;
                        }
                    }
                    case "cont" -> {
                        if (!getObjectField(petition, field).contains(value)) {
                            return false;
                        }
                    }
                    default ->
                            throw new IllegalArgumentException(String.format("Filter operation %s is invalid", operation));
                }
            } else {
                throw new IllegalArgumentException(String.format("Filter %s is invalid", filter));
            }
        }
        return true;
    }

    private String getObjectField(Petition petition, String field) {
        return switch (field) {
            case "name" -> petition.getName();
            case "description" -> petition.getDescription();
            default -> throw new IllegalArgumentException(String.format("Filter has unknown filed %s", field));
        };
    }

    private static List<Petition> dummyPagination(Integer limit, Integer pageNumber, List<Petition> petitionList) {
        if (limit != null) {
            if (limit == 0) {
                throw new IllegalArgumentException("Page size can't be 0");
            }

            if (pageNumber == null) {
                pageNumber = 0;
            }

            petitionList = petitionList.stream()
                    .skip((long) pageNumber * limit)
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return petitionList;
    }
}
