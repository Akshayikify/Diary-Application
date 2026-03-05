package com.diaryapp.diaryapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diaryapp.diaryapp.model.DiaryEntry;
import com.diaryapp.diaryapp.repository.DiaryRepository;

@Service
public class DiaryService {

    @Autowired
    private DiaryRepository repository;

    public DiaryEntry saveEntry(DiaryEntry entry) {
        return repository.save(entry);
    }

    public List<DiaryEntry> getAllEntries() {
        return repository.findAll();
    }
}
