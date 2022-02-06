package com.kataacademy.schoolportal.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kataacademy.schoolportal.common.models.enums.Grade;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {
    private String firstName;
    private String secondName;
    private String lastName;
    private Grade grade;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
