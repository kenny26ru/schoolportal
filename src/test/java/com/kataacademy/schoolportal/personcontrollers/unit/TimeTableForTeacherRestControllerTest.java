package com.kataacademy.schoolportal.personcontrollers.unit;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.TeacherRestController.URL_TEACHER;
import static com.kataacademy.schoolportal.personcontrollers.unit.PersonTestMethods.GET_checkAllPersons;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeTableForTeacherRestControllerTest {

    @DisplayName("Test Norma. GET=>/api/teachers=>200.JSON(List<Teacher>)")
    @Test
    void GET_200_getAllTeachers() throws Exception {
        LocalDate day = LocalDate.now();
        System.out.println(day.getDayOfWeek().getValue());
        System.out.println(DayOfWeek.MONDAY.getValue());
        day = day.minusDays(day.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
        System.out.println(day.getDayOfWeek().getValue());
        assertTrue(true);
    }
}
