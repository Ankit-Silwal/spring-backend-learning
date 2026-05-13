package org.example.springbackendlearning.repository;

import org.example.springbackendlearning.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void register(UserEntity user){
    String sql="Insert into users (email,password)" +
            "values (?,?);";
    jdbcTemplate.update(sql,
            user.getEmail(),
            user.getPassword());
  }

  public UserEntity findByEmail(String email)
  {
    String sql = """
      select * FROM users
      where email = ?
      """;
    try
    {
      return jdbcTemplate.queryForObject(
              sql,
              (rs, rowNum) -> {
                UserEntity user = new UserEntity();
                new BeanPropertyRowMapper<>(UserEntity.class);
                return user;
              },
              email
      );
    }
    catch(Exception e)
    {
      return null;
    }
  }
}
