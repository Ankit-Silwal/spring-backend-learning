package org.example.springbackendlearning.service;

import org.bson.types.ObjectId;
import org.example.springbackendlearning.entity.JournalEntry;
import org.example.springbackendlearning.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;

  public JournalEntry saveEntry(JournalEntry journalEntry){
    return journalEntryRepository.save(journalEntry);
  }

  public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
  }

  public JournalEntry getById(String id){
    return journalEntryRepository.findById(new ObjectId(id)).orElse(null);
  }

  public JournalEntry deleteById(String id){
    JournalEntry entry = journalEntryRepository.findById(new ObjectId(id)).orElse(null);
    if(entry != null){
      journalEntryRepository.deleteById(new ObjectId(id));
    }
    return entry;
  }

  public JournalEntry updateById(String id, JournalEntry journalEntry){
    ObjectId objectId = new ObjectId(id);
    if(journalEntryRepository.existsById(objectId)){
      journalEntry.setId(objectId);
      return journalEntryRepository.save(journalEntry);
    }
    return null;
  }
}
