package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte number;

    private String name;

    private String formName;

    @OneToMany(mappedBy = "form")
    private Set<Pupil> pupilSet;

    @OneToOne(mappedBy = "teacherForm")
    private Teacher formTeacher;

    private String profile;

    @OneToMany(mappedBy = "form")
    private Set<DayTimeTable> timeTables;
