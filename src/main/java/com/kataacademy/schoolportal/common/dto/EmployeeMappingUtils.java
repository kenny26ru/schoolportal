package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMappingUtils {

    //из Teacher в EmployeeDto
    public static EmployeeDto mapToEmployeeDto(Teacher teacher) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(teacher.getFirstName());
        employeeDto.setSecondName(teacher.getSecondName());
        employeeDto.setLastName(teacher.getLastName());
        employeeDto.setBirthday(teacher.getBirthday());
        employeeDto.setGrade(teacher.getGrade());
        return employeeDto;
    }

    //из EmployeeDto в Teacher
//    public static Teacher mapToTeacher(EmployeeDto employeeDto) {
//        Teacher teacher = new Teacher();
//        teacher.setFirstName(employeeDto.getFirstName());
//        teacher.setSecondName(employeeDto.getSecondName());
//        teacher.setLastName(employeeDto.getLastName());
//        teacher.setBirthday(employeeDto.getBirthday());
//        teacher.setGrade(employeeDto.getGrade());
//        return teacher;
//    }
}
