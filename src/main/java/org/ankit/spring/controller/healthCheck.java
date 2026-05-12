package org.ankit.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class healthCheck {
  @GetMapping
  public String getHealth(){
    return "Working";
  }
}
