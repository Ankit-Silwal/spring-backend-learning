package org.example.springbackendlearning.service;

import org.bson.types.ObjectId;
import org.example.springbackendlearning.entity.JournalEntry;
import org.example.springbackendlearning.entity.UserEntity;
import org.example.springbackendlearning.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;
  @Autowired
  private UserService userService;
  public JournalEntry saveEntry(JournalEntry journalEntry, String userName){
    UserEntity user=userService.findByUserName(userName);
    journalEntry.setDate(LocalDateTime.now());
    JournalEntry save = journalEntryRepository.save(journalEntry);
    user.getJournalEntries().add(save);
    userService.saveEntry(user);
    return save;
  }

  public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
  }

  public JournalEntry getById(String id){
    return journalEntryRepository.findById(new ObjectId(id)).orElse(null);
  }

  public void deleteById(String id,String userName){
    UserEntity user=userService.findByUserName(userName);
    user.getJournalEntries().removeIf(x->x.getId().equals(id));
    userService.saveEntry(user);
    journalEntryRepository.deleteById(new ObjectId(id));
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
