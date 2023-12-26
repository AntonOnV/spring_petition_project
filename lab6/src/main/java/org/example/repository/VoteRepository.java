package org.example.repository;

import org.example.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {
    List<Vote> findByUserId(Long userId);

    Vote findByUserIdAndPetitionId(Long userId, Long petitionId);
}
