package org.example.repository.impl;

import org.example.exceptions.NotFoundException;
import org.example.model.Petition;
import org.example.repository.PetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class PetitionRepositoryImpl implements PetitionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer createPetition(String name, String description) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO PETITIONS (name, description) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, name);
            ps.setString(2, description);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public boolean updatePetition(Petition petition)  {
        return 1 == jdbcTemplate.update(
                "Update PETITIONS set name = ?, description = ? where id = ?",
                petition.getName(), petition.getDescription(), petition.getId());
    }

    public void deleteDeletion(Integer id) {
        jdbcTemplate.update("delete from petitions where id =?", id);
    }

    public List<Petition> getPetitionList() {
        return jdbcTemplate.query("select * from petitions",
                 new PetitionRepositoryImpl.PetitionRowMapper());
    }

    public Petition getPetition(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select * from petitions where id=?", new Object[]{
                            id
                    },
                    new BeanPropertyRowMapper<>(Petition.class));
        }catch (Exception ex) {
            throw new NotFoundException(String.format("Petition with id %s not found", id));
        }
    }

    class PetitionRowMapper implements RowMapper<Petition> {
        @Override
        public Petition mapRow(ResultSet rs, int rowNum) throws SQLException {
            Petition petition = new Petition();
            petition.setId(rs.getInt("id"));
            petition.setName(rs.getString("name"));
            petition.setDescription(rs.getString("description"));
            return petition;
        }
    }
}
