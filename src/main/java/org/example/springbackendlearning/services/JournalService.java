package org.example.springbackendlearning.services;

import org.example.springbackendlearning.entity.JournalEntity;
import org.example.springbackendlearning.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService
{
  @Autowired
  private JournalRepository journalRepository;

  public void postJournal(JournalEntity myEntity){
    journalRepository.saveEntry(myEntity);
  }

  public void deleteJournal(int id){
    journalRepository.deleteEntry(id);
  }

  public List<JournalEntity> getJournal(){
    return  journalRepository.get();
  }

  public JournalEntity putJournal(int id,JournalEntity journalEntity){
    return journalRepository.put(id,journalEntity);
  }

}