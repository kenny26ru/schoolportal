package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "pupils")
    private List<Teacher> teachersList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_name")
    private Form form;

    public Pupil(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }
}
