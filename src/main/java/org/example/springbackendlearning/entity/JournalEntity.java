package org.example.springbackendlearning.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class JournalEntity {
  private UUID  id;
  private String title;
  private String description;

}
