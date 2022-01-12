package com.kataacademy.schoolportal.common.models.persons;



import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class Person {

    protected String firstName;
    protected String secondName;
    protected String lastName;
    protected String sex;
    protected LocalDate birthday;
}
