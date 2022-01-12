package com.kataacademy.schoolportal.models.persons;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
