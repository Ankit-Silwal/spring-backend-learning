package org.example.springbackendlearning.service;

import org.bson.types.ObjectId;
import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public UserEntity saveEntry(UserEntity user){
    return userRepository.save(user);
  }

  public List<UserEntity> getAll(){
    return userRepository.findAll();
  }



  public UserEntity findByUserName(String username){
    return userRepository.findByUserName(username);
  }
}
