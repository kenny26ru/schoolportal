package com.kataacademy.schoolportal.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kataacademy.schoolportal.common.models.enums.Grade;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDto {

//    private List<Teacher> listEmployee;

    private String firstName;
    private String secondName;
    private String lastName;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private Grade grade;
}
