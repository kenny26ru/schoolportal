package com.kataacademy.schoolportal.special;

import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;

import java.time.LocalDate;
import java.util.List;

public interface TimeTableForPupil {
    DayTimeTable getDayTimeTable(LocalDate date, long id);
    List<DayTimeTable> getWeekTimeTable();
}
