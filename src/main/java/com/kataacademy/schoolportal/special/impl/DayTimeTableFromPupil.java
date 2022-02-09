package com.kataacademy.schoolportal.special.impl;

import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import com.kataacademy.schoolportal.special.TimeTableForPupil;

import java.time.LocalDate;
import java.util.List;

public class DayTimeTableFromPupil implements TimeTableForPupil {

    private final PupilService pupilService;

    public DayTimeTableFromPupil(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @Override
    public DayTimeTable getDayTimeTable(LocalDate date, long id) {
        return pupilService.getPupilById(id)
                .getForm()
                .getTimeTables()
                .stream()
                .filter(day -> day.getDayTime() == date)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<DayTimeTable> getWeekTimeTable() {
        return null;
    }
}
