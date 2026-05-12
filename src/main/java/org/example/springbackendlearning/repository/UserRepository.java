package org.example.springbackendlearning.repository;

import org.bson.types.ObjectId;
import org.example.springbackendlearning.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
  UserEntity findByUserName(String username);
}
