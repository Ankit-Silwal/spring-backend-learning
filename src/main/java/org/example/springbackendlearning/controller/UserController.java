package org.example.springbackendlearning.controller;

import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<UserEntity>> getAllUsers(){
    return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user){
    UserEntity saved = userService.saveEntry(user);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

  @PutMapping("/{userName}")
  public ResponseEntity<?> updateUser(@RequestBody UserEntity user,@PathVariable String userName){
    UserEntity userInDb = userService.findByUserName(userName);
    if(userInDb != null){
      userInDb.setUserName(user.getUserName());
      userInDb.setPassword(user.getPassword());
      userService.saveEntry(userInDb);
      return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
