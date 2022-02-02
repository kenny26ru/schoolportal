package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ProgressDto {
    byte number;
    Set<SubjectMarks> subjectMarks;

    public ProgressDto(byte number, Set<SubjectMarks> subjectMarks) {
        this.number = number;
        this.subjectMarks = subjectMarks;

    }


}
