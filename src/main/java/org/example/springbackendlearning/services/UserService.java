package org.example.springbackendlearning.services;

import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;


  public void register(UserEntity user){
    UserEntity existingUser=userRepository.findByEmail(user.getEmail());
    if(existingUser!=null){
      throw new RuntimeException("The user already exists");
    }
    userRepository.register(user);
  }

  public boolean login(UserEntity user){
    UserEntity existingUser=userRepository.findByEmail(user.getEmail());
    if(existingUser==null){
      return false;
    }
    return existingUser.getPassword().equals(user.getPassword());
  }
}
