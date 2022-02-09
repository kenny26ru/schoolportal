package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.services.persons.impl.TeacherServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@DisplayName("Тест слоя service для Teacher [TeacherServiceImpl]")
public class TeacherServiceImplTest {

    @TestConfiguration
    static class TeacherServiceImplTestContextConfiguration {
        @Bean
        public TeacherService teacherService() {
            System.out.println("конфиг отработал");
            return new TeacherServiceImpl();
        }
    }

    @Autowired
    private TeacherService service;

    @Test
    @DisplayName("Список всех учителей")
    void test_getAllTeachers() {
        System.out.println("service = " + service);
        List<Teacher> actual = service.getAllTeachers();
        System.out.println("actual = "+ actual);
    }
}
