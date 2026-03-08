package com.diaryapp.diaryapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public String analyzeSentiment(String text) {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:5000/predict";

        Map<String, String> request = new HashMap<>();

        request.put("text", text);

        Map response = restTemplate.postForObject(url, request, Map.class);

        return response.get("sentiment").toString();
    }
}
