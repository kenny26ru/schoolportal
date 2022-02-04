package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject_name")
    @Enumerated(EnumType.STRING)
    private SchoolSubjects subjectName;

    @Column(name = "date_lesson")
    private LocalDate date;

    @Column(name = "time_start")
    private LocalTime timeStart;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    @Column(name = "number_classroom")
    private Integer numberClassroom;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "id")
    private Teacher teacher;

    public Lesson(SchoolSubjects subjectName, LocalDate dateLesson, LocalTime timeStart, LocalTime timeEnd, Integer numberClassroom) {
        this.subjectName = subjectName;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numberClassroom = numberClassroom;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", date=" + date +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", numberClassroom=" + numberClassroom +
                ", teacher=" + teacher +
                '}';
    }

}
