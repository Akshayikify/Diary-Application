package com.diaryapp.diaryapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.diaryapp.diaryapp.model.DiaryEntry;

public interface DiaryRepository extends MongoRepository<DiaryEntry, String> {
}
