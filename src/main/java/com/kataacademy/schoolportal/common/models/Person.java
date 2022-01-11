package com.kataacademy.schoolportal.common.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public abstract class Person {

    private String firstName;
    private String secondName;
    private String lastName;
    private String sex;
    private LocalDate birthday;
}
