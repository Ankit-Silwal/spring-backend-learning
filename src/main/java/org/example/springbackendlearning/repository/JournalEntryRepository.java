package org.example.springbackendlearning.repository;

import org.bson.types.ObjectId;
import org.example.springbackendlearning.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

