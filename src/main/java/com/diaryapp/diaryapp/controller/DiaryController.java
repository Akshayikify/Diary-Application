package com.diaryapp.diaryapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.diaryapp.diaryapp.model.DiaryEntry;
import com.diaryapp.diaryapp.service.DiaryService;

@RestController
@RequestMapping("/diary")
public class DiaryController {

    @Autowired
    private DiaryService service;

    @PostMapping
    public DiaryEntry addEntry(@RequestBody DiaryEntry entry) {
        return service.saveEntry(entry);
    }

    @GetMapping
    public List<DiaryEntry> getEntries() {
        return service.getAllEntries();
    }
}
