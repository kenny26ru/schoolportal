package com.kataacademy.schoolportal.common.repository.schooattribute;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findLessonByDate(LocalDate date);

    List<Lesson> findLessonByTeacher(Teacher teacher);

    List<Lesson> findLessonByTeacherAndDate(Teacher teacher, LocalDate date);

    List<Lesson> findLessonByTeacherAndDateIsBetween(Teacher teacher, LocalDate monday, LocalDate sunday);

    List<Lesson> findLessonByTeacherAndDateBetween(Teacher teacher, LocalDate monday, LocalDate sunday);

    List<Lesson> findLessonByTimeStart(LocalTime timeStart);

    List<Lesson> findLessonByTimeEnd(LocalTime timeEnd);

    List<Lesson> findLessonBySubjectName(String subjectName);

    List<Lesson> findLessonByNumberClassroom(Integer numberClassroom);
}
