package com.kataacademy.schoolportal.common.services.schooattribute;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LessonService {

    List<Lesson> getAllLessons();

    Lesson getLessonById(Long id);

    Lesson saveLesson(Lesson lesson);

    Lesson editLesson(Lesson school);

    void deleteLesson(Long id);

    List<Lesson> getLessonsByDate(LocalDate date);

    List<Lesson> getLessonsByTeacher(Teacher teacher);

    List<Lesson> getLessonsByTeacherAndDate(Teacher teacher, LocalDate date);

    List<Lesson> getLessonsByTeacherAndDateIsBetween(Teacher teacher, LocalDate monday, LocalDate sunday);

    List<Lesson> getLessonsByTeacherAndDateBetween(Teacher teacher, LocalDate monday, LocalDate sunday);

    List<Lesson> getLessonsByTimeStart(LocalTime timeStart);

    List<Lesson> getLessonsByTimeEnd(LocalTime timeEnd);

    List<Lesson> getLessonsBySubjectName(String subjectName);

    List<Lesson> getLessonsByNumberClassroom(Integer numberClassroom);
}
