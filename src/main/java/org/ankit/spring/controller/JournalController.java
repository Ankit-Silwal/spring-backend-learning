package org.ankit.spring.controller;

import org.ankit.spring.entity.JournalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")



public class JournalController {
  private Map<Integer,JournalEntity> journalEntries= new HashMap<>();
  @GetMapping
  public List<JournalEntity> getAlllist(){
    return new ArrayList<>(journalEntries.values());
  }
  @GetMapping("/{id}")
  public JournalEntity get(@PathVariable int id){
    return journalEntries.get(id);
  }
  @PostMapping
  public void post(@RequestBody JournalEntity myEntity){
    journalEntries.put(myEntity.getId(),myEntity);
  }

  @PutMapping("/{id}")
  public void put(@RequestBody JournalEntity myEntity,@PathVariable int id){
    journalEntries.put(id,myEntity);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id){
    journalEntries.remove(id);
  }

}
