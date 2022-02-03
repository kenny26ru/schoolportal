package com.kataacademy.schoolportal.common.models.persons;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "directors")
public class Director extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Director(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Director(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }
}
