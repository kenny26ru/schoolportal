package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "forms")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte number;

    private String name;

    private String formName;

    @OneToMany(mappedBy = "form", fetch = FetchType.EAGER)
    private List<Pupil> pupilList;

    @ManyToMany(mappedBy = "forms")
    private List<Teacher> formTeacher;

    private String profile;

}
