package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.repository.persons.TeacherRepository;
import com.kataacademy.schoolportal.common.services.persons.impl.TeacherServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@DataJpaTest
//@JdbcTest
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@TestPropertySource(locations = "classpath: application.properties")
@DisplayName("Тест слоя service для Teacher [TeacherServiceImpl]")
public class TeacherServiceImplTest {

    @Autowired
    @Qualifier("teacherRepository")
    private TeacherRepository repository;

    @BeforeAll
    static void start() {
        System.out.println("Стартовали");
    }

    @AfterAll
    static void stop() {
        System.out.println("Финишировали");
    }

//
//    @Autowired
//    private TeacherService service;

    @Test
    @DisplayName("Список всех учителей")
    void test_getAllTeachers() {
        System.out.println("А это тест уже!");
        Assertions.assertNotNull(repository, "Ошибка: Repository равен null");
//        Assertions.assertNotNull(service, "Ошибка: Service равен null");
//        System.out.println("service = " + service);
//        List<Teacher> actual = service.getAllTeachers();
//        System.out.println("actual = "+ actual);
    }
}
