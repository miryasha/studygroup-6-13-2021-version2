package com.company.demo.dao;

import com.company.demo.model.Car;
import com.company.demo.model.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class NameDaoTemp implements NameDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_NAME_SQL =
            "insert into name (name) values( ?)";
    private static final String SELECT_NAME_SQL =
            "select * from name where name_id = ?";


@Autowired
    public NameDaoTemp(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}


    @Override
    public Name getName(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_NAME_SQL,
                    this::mapRowToName,
                    id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }

    }

    @Override
    @Transactional
    public Name addName(Name name) {
            jdbcTemplate.update(
                    INSERT_NAME_SQL,
                    name.getName()
            );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        name.setId(id);

        return name;

    }

    private Name mapRowToName(ResultSet rs, int rowNum) throws SQLException {
      Name name = new Name();
      name.setId(rs.getInt("name_id"));
      name.setName(rs.getString("name"));


        return name;
    }


}
