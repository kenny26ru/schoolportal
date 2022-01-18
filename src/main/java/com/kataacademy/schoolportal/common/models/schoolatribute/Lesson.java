package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "date_lesson")
    private LocalDate dateLesson;

    @Column(name = "time_start")
    private LocalTime timeStart;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    @Column(name = "number_classroom")
    private Integer numberClassroom;

    public Lesson() {
    }

    public Lesson(String subjectName, LocalDate dateLesson, LocalTime timeStart, LocalTime timeEnd, Integer numberClassroom) {
        this.subjectName = subjectName;
        this.dateLesson = dateLesson;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.numberClassroom = numberClassroom;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", dateLesson=" + dateLesson +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", numberClassroom=" + numberClassroom +
                '}';
    }
}
