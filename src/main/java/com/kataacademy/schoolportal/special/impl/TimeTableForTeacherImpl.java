package com.kataacademy.schoolportal.special.impl;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;
import com.kataacademy.schoolportal.common.services.schooattribute.LessonService;
import com.kataacademy.schoolportal.special.TimeTableForTeacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

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
    public List<DayTimeTable> getWeekTimeTable() {
        return null;
    }
}
