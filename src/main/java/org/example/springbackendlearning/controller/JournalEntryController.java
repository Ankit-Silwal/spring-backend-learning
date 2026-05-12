package org.example.springbackendlearning.controller;
import org.example.springbackendlearning.entity.JournalEntry;
import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.service.JournalEntryService;
import org.example.springbackendlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserService userService;
  @PostMapping("/{userName}")
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName){
    try{
      journalEntryService.saveEntry(myEntry,userName);
      return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    }catch (Exception e){
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/{userName}")
  public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUsers(@PathVariable String userName){
    UserEntity user=userService.findByUserName(userName);
    List<JournalEntry> all=user.getJournalEntries();
    if(all!=null&&!all.isEmpty()){
      return new ResponseEntity<>(all,HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String myId){
    try {
      JournalEntry journalEntry = journalEntryService.getById(myId);
      if(journalEntry != null){
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/id/{userName}/{myId}")
  public ResponseEntity<Void> deleteJournalEntryById(@PathVariable String userName,@PathVariable String myId) {
    journalEntryService.deleteById(myId,userName);
    return new ResponseEntity<>(HttpStatus.OK);

  }

  @PutMapping("/id/{userName}/{myId}")
  public ResponseEntity<JournalEntry> updateJournalById(@PathVariable String userName, @PathVariable String myId,@RequestBody JournalEntry myEntry){
    try {
      JournalEntry updatedEntry = journalEntryService.updateById(myId, myEntry);
      if(updatedEntry != null){
        return new ResponseEntity<>(updatedEntry, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
