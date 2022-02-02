package com.kataacademy.schoolportal.common.services.schooattribute.impl;

import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import com.kataacademy.schoolportal.common.repository.schooattribute.LessonRepository;
import com.kataacademy.schoolportal.common.services.schooattribute.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository repository;

    @Override
    public List<Lesson> getAllLessons() {
        return null;
    }

    @Override
    public List<Lesson> getLessonsAtDate(LocalDate dateLesson) {
        return null;
    }

    @Override
    public List<Lesson> getLessonsForTeacher(Long idTeacher) {
        return null;
    }

    @Override
    public List<Lesson> getLessonsForTeacherAtDate(Long idTeacher, LocalDate dateLesson) {
        return null;
    }

    @Override
    public Lesson getLessonById(Long id) {
        return null;
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        return null;
    }

    @Override
    public Lesson editLesson(Lesson school) {
        return null;
    }

    @Override
    public void deleteLesson(Long id) {

    }
}
