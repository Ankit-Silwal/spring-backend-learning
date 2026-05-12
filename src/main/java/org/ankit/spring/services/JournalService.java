package org.ankit.spring.services;

import org.ankit.spring.entity.JournalEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JournalService
{
  private List<JournalEntity> journalEntries = new ArrayList<>();

}