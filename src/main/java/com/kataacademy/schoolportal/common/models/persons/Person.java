package com.kataacademy.schoolportal.common.models.persons;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Person {

    private String firstName;
    private String secondName;
    private String lastName;
    private String sex;
    private LocalDate birthday;
}
