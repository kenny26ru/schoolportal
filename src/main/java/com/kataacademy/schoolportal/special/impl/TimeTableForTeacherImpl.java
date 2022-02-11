package com.kataacademy.schoolportal.special.impl;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;
import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import com.kataacademy.schoolportal.common.services.schooattribute.LessonService;
import com.kataacademy.schoolportal.special.TimeTableForTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TimeTableForTeacherImpl implements TimeTableForTeacher {

    @Autowired
    private LessonService service;

    @Override
    public DayTimeTable getDayTimeTable(Teacher teacher, LocalDate date) {
        DayTimeTable table = new DayTimeTable();
        table.setDayTime(date);
        table.setLessons(new HashSet<>(service.getLessonsByTeacherAndDate(teacher, date)));
        return table;
    }

    @Override
    public List<DayTimeTable> getWeekTimeTable(Teacher teacher, LocalDate date) {
        List<DayTimeTable> list = new ArrayList<>();
        LocalDate monday = date.minusDays((long)(date.getDayOfWeek().getValue()) - DayOfWeek.MONDAY.getValue());
        LocalDate sunday = date.plusDays((long)(DayOfWeek.SUNDAY.getValue()) - date.getDayOfWeek().getValue());
        List<Lesson> lessons = service.getLessonsByTeacherAndDateBetween(teacher, monday, sunday);
        do {
            LocalDate finalMonday = monday;
            list.add(new DayTimeTable(monday,
                        lessons
                        .stream()
                        .filter(x -> x.getDate().isEqual(finalMonday))
                        .collect(Collectors.toSet())
                    ));
            monday = monday.plusDays(1);
        } while (monday.getDayOfWeek() != DayOfWeek.MONDAY);
        return list;
    }
}
