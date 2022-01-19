package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="teachers")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teacher_forms",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "form_id")
    )
    private Set<Form> forms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "teacher_pupils",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id")
    )
    private Set<Pupil> pupils;

    public Teacher(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

}
