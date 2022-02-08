package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forms")
public class Form implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte number;

    private String name;

    private String formName;

    @OneToMany(mappedBy = "form")
    private Set<Pupil> pupilSet = new HashSet<>();

    @OneToOne(mappedBy = "teacherForm")
    private Teacher formTeacher;

    private String profile;

    @ManyToOne
    @JoinColumn(name = "school_id", referencedColumnName = "id", nullable = false)
    private School school;

}
