package org.example.springbackendlearning.controller;

import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public String register(@RequestBody UserEntity user){
    try{
      userService.register(user);
      return "User registered successfully";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
  @PostMapping("/login")
  public String login(@RequestBody UserEntity user){
    boolean correct= userService.login(user);
    if(correct){
      return "Successfully logged in";
    }else{
      return "Failed to login";
    }
  }

}
