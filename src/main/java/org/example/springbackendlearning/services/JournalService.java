package org.example.springbackendlearning.services;

import org.example.springbackendlearning.entity.JournalEntity;
import org.example.springbackendlearning.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JournalService
{
  @Autowired
  private JournalRepository journalRepository;

  public void postJournal(JournalEntity myEntity){
    journalRepository.saveEntry(myEntity);
  }

  public void deleteJournal(UUID id){
    journalRepository.deleteEntry(id);
  }

  public List<JournalEntity> getJournal(){
    return  journalRepository.get();
  }

  public JournalEntity putJournal(UUID id, JournalEntity journalEntity){
    return journalRepository.put(id,journalEntity);
  }

  public JournalEntity getJournalById(UUID id){
    return journalRepository.get(id);
  }

}