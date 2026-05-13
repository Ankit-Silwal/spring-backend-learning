package org.example.springbackendlearning.controller;

import org.example.springbackendlearning.entity.JournalEntity;
import org.example.springbackendlearning.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
  @Autowired
  JournalService journalService;
  @GetMapping
  public List<JournalEntity> get(){
    return journalService.getJournal();
  }
  @PostMapping
  public void post(@RequestBody JournalEntity journalEntity){
    journalService.postJournal(journalEntity);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable int id){
    journalService.deleteJournal(id);
  }

  @PutMapping("/{id}")
  public JournalEntity put(@PathVariable int id,@RequestBody JournalEntity journalEntity){
    return journalService.putJournal(id,journalEntity);
  }

  @GetMapping("/{id}")
  public JournalEntity getById(@PathVariable int id){
    return journalService.getJournalById(id);
  }

}
