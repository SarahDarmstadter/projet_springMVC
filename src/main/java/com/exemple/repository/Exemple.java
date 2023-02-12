package com.exemple.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class Exemple {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public Exemple(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

//  public List<Exemple> getExemples() {
//    String sql = "SELECT * FROM exemples";
//    return jdbcTemplate.query(sql, new ExempleRowMapper());
//  }

  // ... d'autres méthodes d'opération CRUD ...
}
