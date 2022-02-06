package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import com.kataacademy.schoolportal.common.repository.schooattribute.SchoolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    public List<Teacher> findAllTeachersBySchoolId(School school) {
        return school.getTeacherList();
    }
}
