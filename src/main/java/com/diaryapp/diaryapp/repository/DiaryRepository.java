package com.diaryapp.diaryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.diaryapp.diaryapp.model.DiaryEntry;

public interface DiaryRepository extends JpaRepository<DiaryEntry, Long> {
}
