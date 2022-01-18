package com.kataacademy.schoolportal.common.models.schoolatribute;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "homework")
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

    public HomeWork(String name, LocalDate data, String description) {
        this.data = data;
        this.name = name;
        this.description = description;
    }
}
