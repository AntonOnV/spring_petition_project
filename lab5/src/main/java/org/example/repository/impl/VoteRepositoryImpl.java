package org.example.repository.impl;

import org.example.model.Vote;
import org.example.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Vote saveVote(Vote vote) {
        jdbcTemplate.update(
                "INSERT INTO VOTES VALUES (?, ?, ?, ?)", vote.getId(),
                vote.getPetitionId(), vote.getId(), vote.getVotedAt());

        return vote;
    }

    @Override
    public Optional<Vote> getVoteById(String voteId) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("select * from votes where id=?", new Object[] {
                        voteId
                },
                new BeanPropertyRowMapper<>(Vote.class)));
    }

    @Override
    public List<Vote> getVotesByPetitionId(Integer petitionId) {
        return jdbcTemplate.query("select * from votes where petitionId=?", new Object[] {
                petitionId
                }
                , new VoteRowMapper());
    }

    @Override
    public List<Vote> getAll() {
        return jdbcTemplate.query("select * from votes"
                , new VoteRowMapper());
    }

    class VoteRowMapper implements RowMapper<Vote> {
        @Override
        public Vote mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vote vote = new Vote();
            vote.setId(rs.getString("id"));
            vote.setUserId(rs.getString("userId"));
            vote.setPetitionId(rs.getInt("petitionId"));
            vote.setVotedAt(rs.getObject("votedAt", LocalDateTime.class));
            return vote;
        }
    }
}
