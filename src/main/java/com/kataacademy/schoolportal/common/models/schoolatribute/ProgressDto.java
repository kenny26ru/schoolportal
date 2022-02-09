package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
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

    public ProgressDto(Form form, Pupil pupil) {
        this.number = form.getNumber();
        this.subjectMarks = pupil.getSubjectMarks();

    }

    @Override
    public String toString() {
        return "ProgressDto{" +
                "number=" + number +
                ", subjectMarks=" + subjectMarks.toString() +
                '}';
    }

}
