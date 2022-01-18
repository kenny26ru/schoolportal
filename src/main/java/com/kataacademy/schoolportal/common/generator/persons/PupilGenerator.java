package com.kataacademy.schoolportal.common.generator.persons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Component
@Getter
@Setter
public class PupilGenerator {
    private String firstName;
    private String secondName;
    private String lastName;

    @Pattern(regexp = "^[M|F]{1}$", message = "Must be M or F")
    private String sex;

    @Min(value = 6, message = "Age should not be less than 6")
    @Max(value = 18, message = "Age should not be greater than 18")
    private Byte age;

    public PupilGenerator() {
    }

    public PupilGenerator(String firstName, String secondName, String lastName, String sex, Byte age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
    }

}
