package com.kataacademy.schoolportal.common.repository.persons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@JdbcTest
public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    @DisplayName("Create User Test ")
    void createUserTest() {
        System.out.println("ТЕСТ");
    }
}
