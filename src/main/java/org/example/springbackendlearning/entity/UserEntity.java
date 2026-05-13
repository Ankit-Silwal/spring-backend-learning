package org.example.springbackendlearning.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class UserEntity {
  private UUID id;
  private String email;
  private String password;

}
