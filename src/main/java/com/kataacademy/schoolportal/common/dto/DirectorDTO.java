package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class DirectorDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private String sex;
    private LocalDate birthday;

    private Set<Teacher> teacherSet;
}
