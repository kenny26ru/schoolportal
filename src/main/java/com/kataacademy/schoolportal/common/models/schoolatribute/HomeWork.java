package com.kataacademy.schoolportal.common.models.schoolatribute;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor

@Getter @Setter
public class HomeWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "homework_name")
    private String name;

    @Column(name = "homework_data")
    private LocalDate data;

    @Column(name = "homework_description")
    private String description;
}
