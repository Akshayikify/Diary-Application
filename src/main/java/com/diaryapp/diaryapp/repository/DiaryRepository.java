package com.diaryapp.diaryapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.diaryapp.diaryapp.model.DiaryEntry;

public interface DiaryRepository extends MongoRepository<DiaryEntry, String> {

    List<DiaryEntry> findByUsername(String username);

}
