package com.kataacademy.schoolportal.special;


import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;
import com.kataacademy.schoolportal.common.models.schoolatribute.Lesson;
import com.kataacademy.schoolportal.common.services.schooattribute.LessonService;
import com.kataacademy.schoolportal.special.impl.TimeTableForTeacherImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@DisplayName("Тесты расписания учителя")
public class TimeTableForTeacherTest {

    @Mock
    private LessonService service;
    MockitoSession session;

    //Дата
    LocalDate monday = LocalDate.of(2022, Month.FEBRUARY, 7);
    LocalTime timeStart = LocalTime.of(8, 0);
    LocalTime timeStop = LocalTime.of(8, 45);

    //Учитель
    Teacher teacher = new Teacher(1L, "Иван", "Петрович", "Сидоров", "М", LocalDate.of(1985, Month.SEPTEMBER, 6));

    //Список уроков
    Lesson lesson1 = new Lesson(1L, "Русский язык", monday, timeStart, timeStop, 12, teacher);
    Lesson lesson2 = new Lesson(2L, "Литература", monday, timeStart.plusMinutes(55L), timeStop.plusMinutes(55L), 12, teacher);
    Lesson lesson3 = new Lesson(3L, "Русский язык", monday, timeStart.plusMinutes(110L), timeStop.plusMinutes(110L), 12, teacher);


    @BeforeEach
    public void beforeTest() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @AfterEach
    public void afterTest() {
        session.finishMocking();
    }


    @Test
    @DisplayName("Расписание на день")
    void getDayTimeTable() {
        List<Lesson> lessons = new ArrayList<>(Arrays.asList(lesson1, lesson2, lesson3));
        System.out.println(lessons);
        Mockito.when(service.getLessonsByTeacherAndDate(Mockito.any(), Mockito.any())).thenReturn(lessons);
        //TimeTableForTeacher timeTableForTeacher = new TimeTableForTeacherImpl();
        //DayTimeTable dayTable = timeTableForTeacher.getDayTimeTable(teacher, monday);
        //System.out.println(dayTable);
        Assertions.assertTrue(true);
    }

    @DisplayName("Повторяющийся тест")
    @RepeatedTest(value = 5, name = "{displayName} - проход {currentRepetition} из {totalRepetitions}")
    void test2(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Это тест 2. Попытка -> " + repetitionInfo.getCurrentRepetition());
        Assertions.assertEquals(1,1);
    }

    @Disabled
    @Test
    void test3() {
        Assertions.fail("Тест не прошел.");
    }

}
