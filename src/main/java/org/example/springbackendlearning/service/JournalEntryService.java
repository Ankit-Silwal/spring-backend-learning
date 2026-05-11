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

  public void saveEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry);
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
    journalEntry.setId(new ObjectId(id));
    return journalEntryRepository.save(journalEntry);
  }
}
