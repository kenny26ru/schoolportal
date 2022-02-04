package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ProgressDto implements Serializable {

    private static final long serialVersionUID = 1L;

    byte number;
    List<SubjectMarks> subjectMarks;

    public ProgressDto(byte number, List<SubjectMarks> subjectMarks) {
        this.number = number;
        this.subjectMarks = subjectMarks;

    }

    @Override
    public String toString() {
        return "ProgressDto{" +
                "number=" + number +
                ", subjectMarks=" + subjectMarks.toString() +
                '}';
    }

}
