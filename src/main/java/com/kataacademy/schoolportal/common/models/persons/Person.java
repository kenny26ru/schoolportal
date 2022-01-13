package com.kataacademy.schoolportal.common.models.persons;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
public abstract class Person {

    private String firstName;
    private String secondName;
    private String lastName;
    private String sex;
    private LocalDate birthday;
}
