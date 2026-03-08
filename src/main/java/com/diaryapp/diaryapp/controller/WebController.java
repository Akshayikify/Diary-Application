package com.diaryapp.diaryapp.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.diaryapp.diaryapp.model.DiaryEntry;
import com.diaryapp.diaryapp.model.User;
import com.diaryapp.diaryapp.repository.DiaryRepository;
import com.diaryapp.diaryapp.repository.UserRepository;

@Controller
public class WebController {

    @Autowired
    DiaryRepository diaryRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        List<DiaryEntry> diaries = diaryRepo.findByUsername(principal.getName());
        model.addAttribute("diaries", diaries);
        return "dashboard";
    }

    @GetMapping("/new")
    public String newDiary() {
        return "new-diary";
    }

    @PostMapping("/save")
    public String saveDiary(@RequestParam String title, @RequestParam String content, Principal principal) {
        DiaryEntry entry = new DiaryEntry();
        entry.setTitle(title);
        entry.setContent(content);
        entry.setUsername(principal.getName());
        diaryRepo.save(entry);
        return "redirect:/dashboard";
    }

    @GetMapping("/view/{id}")
    public String viewDiary(@PathVariable String id, Model model) {
        Optional<DiaryEntry> entry = diaryRepo.findById(id);
        entry.ifPresent(e -> model.addAttribute("diary", e));
        return "view-diary";
    }

    @GetMapping("/edit/{id}")
    public String editDiary(@PathVariable String id, Model model) {
        Optional<DiaryEntry> entry = diaryRepo.findById(id);
        entry.ifPresent(e -> model.addAttribute("diary", e));
        return "edit-diary";
    }

    @PostMapping("/update/{id}")
    public String updateDiary(@PathVariable String id, @RequestParam String title, @RequestParam String content) {
        Optional<DiaryEntry> optional = diaryRepo.findById(id);
        if (optional.isPresent()) {
            DiaryEntry entry = optional.get();
            entry.setTitle(title);
            entry.setContent(content);
            diaryRepo.save(entry);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/delete/{id}")
    public String deleteDiary(@PathVariable String id) {
        diaryRepo.deleteById(id);
        return "redirect:/dashboard";
    }
}
