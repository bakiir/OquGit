package com.example.books.controller;

import com.example.books.service.LessonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request) {
        lessonService.deleteLesson(id);
        String referer = request.getHeader("Referer"); // Получаем предыдущий URL
        return "redirect:" + (referer != null ? referer : "/"); // Если referer null, редиректим на главную
    }
}
