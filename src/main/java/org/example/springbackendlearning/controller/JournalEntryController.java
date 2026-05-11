package org.example.springbackendlearning.controller;
import org.example.springbackendlearning.entity.JournalEntry;
import org.example.springbackendlearning.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;

  @PostMapping
  public boolean createEntry(@RequestBody JournalEntry myEntry){
    myEntry.setDate(LocalDateTime.now());
    journalEntryService.saveEntry(myEntry);
    return true;
  }
  @GetMapping
  public List<JournalEntry> getAllEntries(){
    return journalEntryService.getAll();
  }

  @GetMapping("/id/{myId}")
  public JournalEntry getJournalEntryById(@PathVariable String myId){
    return journalEntryService.getById(myId);
  }

  @DeleteMapping("/id/{myId}")
  public JournalEntry deleteJournalEntryById(@PathVariable String myId){
    return journalEntryService.deleteById(myId);
  }

  @PutMapping("/id/{myId}")
  public JournalEntry updateJournalById(@PathVariable String myId,@RequestBody JournalEntry myEntry){
    return journalEntryService.updateById(myId, myEntry);
  }
}
