package com.kataacademy.schoolportal.common.services.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonService {

    public List<Lesson> getAllLessons();

    public List<Lesson> getLessonsAtDate(LocalDate dateLesson);

    public List<Lesson> getLessonsForTeacher(Long idTeacher);

    public List<Lesson> getLessonsForTeacherAtDate(Long idTeacher, LocalDate dateLesson);

    public Lesson getLessonById(Long id);

    public Lesson saveLesson(Lesson lesson);

    public Lesson editLesson(Lesson school);

    public void deleteLesson(Long id);
}
