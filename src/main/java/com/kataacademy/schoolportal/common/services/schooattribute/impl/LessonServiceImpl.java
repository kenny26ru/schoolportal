package com.kataacademy.schoolportal.common.services.schooattribute.impl;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import com.kataacademy.schoolportal.common.repository.schooattribute.LessonRepository;
import com.kataacademy.schoolportal.common.services.schooattribute.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository repository;

    @Override
    public List<Lesson> getAllLessons() {
        return repository.findAll();
    }

    @Override
    public Lesson getLessonById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Lesson saveLesson(Lesson lesson) {
        return repository.save(lesson);
    }

    @Override
    public Lesson editLesson(Lesson lesson) {
        return repository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Lesson> getLessonsByDate(LocalDate date) {
        return repository.findLessonByDate(date);
    }

    @Override
    public List<Lesson> getLessonsByTeacher(Teacher teacher) {
        return repository.findLessonByTeacher(teacher);
    }

    @Override
    public List<Lesson> getLessonsByTeacherAndDate(Teacher teacher, LocalDate date) {
        return repository.findLessonByTeacherAndDate(teacher, date);
    }

    @Override
    public List<Lesson> getLessonsByTeacherAndDateIsBetween(Teacher teacher, LocalDate monday, LocalDate sunday) {
        return repository.findLessonByTeacherAndDateIsBetween(teacher, monday, sunday);
    }

    @Override
    public List<Lesson> getLessonsByTeacherAndDateBetween(Teacher teacher, LocalDate monday, LocalDate sunday) {
        return repository.findLessonByTeacherAndDateBetween(teacher, monday, sunday);
    }

    @Override
    public List<Lesson> getLessonsByTimeStart(LocalTime timeStart) {
        return repository.findLessonByTimeStart(timeStart);
    }

    @Override
    public List<Lesson> getLessonsByTimeEnd(LocalTime timeEnd) {
        return repository.findLessonByTimeEnd(timeEnd);
    }

    @Override
    public List<Lesson> getLessonsBySubjectName(String subjectName) {
        return repository.findLessonBySubjectName(subjectName);
    }

    @Override
    public List<Lesson> getLessonsByNumberClassroom(Integer numberClassroom) {
        return repository.findLessonByNumberClassroom(numberClassroom);
    }
}
