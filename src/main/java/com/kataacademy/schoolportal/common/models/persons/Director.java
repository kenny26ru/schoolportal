package com.kataacademy.schoolportal.common.models.persons;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "directors")
public class Director extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "director")
    private Set<Teacher> teacherSet;

    @OneToOne
    @JoinColumn(name="school_id")
    private School school;

    public Director(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Director(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }
}
