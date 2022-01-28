package com.kataacademy.schoolportal.special;

import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;

import java.util.List;

public interface TimeTableForTeacher{
    DayTimeTable getDayTimeTable();
    List<DayTimeTable> getWeekTimeTable();
}
