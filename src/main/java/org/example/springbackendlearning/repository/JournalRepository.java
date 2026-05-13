package org.example.springbackendlearning.repository;

import org.example.springbackendlearning.entity.JournalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JournalRepository
{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void saveEntry(JournalEntity journalEntity)
  {
    String sql = "INSERT INTO journal_entries(title, content) VALUES (?, ?);";

    jdbcTemplate.update(
            sql,
            journalEntity.getTitle(),
            journalEntity.getDescription()
    );
  }

  public void deleteEntry(UUID id)
  {
    String sql = "DELETE FROM journal_entries WHERE id = ?";

    jdbcTemplate.update(
            sql,
            id
    );
  }

  public List<JournalEntity> get()
  {
    String sql = "SELECT * FROM journal_entries";

    return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
  }

  public JournalEntity get(UUID id)
  {
    String sql = "SELECT * FROM journal_entries WHERE id = ?";
    return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> mapRow(rs),
            id
    );
  }
  public JournalEntity put(UUID id, JournalEntity journalEntity)
  {
    String sql = """
        UPDATE journal_entries
        SET title = ?, content = ?
        WHERE id = ?
        """;
    jdbcTemplate.update(
            sql,
            journalEntity.getTitle(),
            journalEntity.getDescription(),
            id
    );
    return journalEntity;
  }

  private JournalEntity mapRow(java.sql.ResultSet rs) throws java.sql.SQLException
  {
    JournalEntity journalEntity = new JournalEntity();

    journalEntity.setId(rs.getObject("id",UUID.class));
    journalEntity.setTitle(rs.getString("title"));
    journalEntity.setDescription(rs.getString("content"));
    return journalEntity;
  }
}