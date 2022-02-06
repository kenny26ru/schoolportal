package com.kataacademy.schoolportal.special;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;

import java.time.LocalDate;
import java.util.List;

public interface TimeTableForTeacher{
    DayTimeTable getDayTimeTable(Teacher teacher, LocalDate date);
    List<DayTimeTable> getWeekTimeTable(Teacher teacher, LocalDate date);
}
