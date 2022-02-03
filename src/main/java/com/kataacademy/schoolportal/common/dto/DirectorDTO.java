package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Data;

import java.util.Set;

@Data
public class DirectorDTO {
    private Set<Teacher> teacherSet;
}
