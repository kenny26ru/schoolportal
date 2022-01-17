package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.persons.validate.SizeNotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@MappedSuperclass
public abstract class Person {

    @SizeNotBlank(min = 4, message = "Поле firstName минимум 4 символа")
    private String firstName;
    @SizeNotBlank(min = 4, message = "Поле secondName минимум 4 символа")
    private String secondName;
    @SizeNotBlank(min = 4, message = "Поле lastName минимум 4 символа")
    private String lastName;
    //TODO как сделать влидацию на конкретные значения? Может есть VALUE
    @Size(min = 1, max = 1, message = "Поле sex 1 символ: f или m")
    private String sex;
    //TODO как делать валидацию времени?
    private LocalDate birthday;
}
