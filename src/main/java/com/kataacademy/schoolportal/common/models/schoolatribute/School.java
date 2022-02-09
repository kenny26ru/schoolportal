package com.kataacademy.schoolportal.common.models.schoolatribute;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "school")
    private Set<Form> forms;

    public School() {
    }

    public School(String name, int capacity, String address) {
        this.name = name;
        this.capacity = capacity;
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", address='" + address + '\'' +
                '}';
    }
}
