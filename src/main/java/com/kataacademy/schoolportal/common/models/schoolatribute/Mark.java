package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "marks")
public class Mark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
     * Аннотацию @ManyToOne не стал добавлять, т.к. SchoolSubjects - это Enum.
     * Вместо этого поставил @Enumerated(EnumType.STRING)
     */
    @Column(name = "school_subject")
    @Enumerated(EnumType.STRING)
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private SchoolSubjects schoolSubjects;

    @Min(2)
    @Max(5)
    private int mark;

    private LocalDate dayTime;
}
