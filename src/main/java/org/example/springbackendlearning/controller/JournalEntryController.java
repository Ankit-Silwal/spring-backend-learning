package org.example.springbackendlearning.controller;
import org.example.springbackendlearning.entity.JournalEntry;
import org.example.springbackendlearning.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

  @Autowired
  private JournalEntryService journalEntryService;

  @PostMapping
  public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
    myEntry.setDate(LocalDateTime.now());
    JournalEntry savedEntry = journalEntryService.saveEntry(myEntry);
    return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<JournalEntry>> getAllEntries(){
    return new ResponseEntity<>(journalEntryService.getAll(), HttpStatus.OK);
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

  @DeleteMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> deleteJournalEntryById(@PathVariable String myId){
    try {
      JournalEntry deletedEntry = journalEntryService.deleteById(myId);
      if(deletedEntry != null){
        return new ResponseEntity<>(deletedEntry, HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/id/{myId}")
  public ResponseEntity<JournalEntry> updateJournalById(@PathVariable String myId,@RequestBody JournalEntry myEntry){
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
