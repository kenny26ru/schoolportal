package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter @Setter
public class Mark {

    @Enumerated(EnumType.STRING)
    private SchoolSubjects schoolSubjects;

    @Min(2)
    @Max(5)
    private int mark;

    private LocalDate dayTime;
}
