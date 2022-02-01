package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import com.kataacademy.schoolportal.common.models.schoolatribute.SubjectMarks;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
     * Аннотация @JoinColumn нужна для настройки имени столбца в таблице pupils,
     * который сопоставляется с первичным ключом в таблице forms.
     * */
    @ManyToOne
    @JoinColumn(name="form_name", referencedColumnName="formName", nullable = false)
    private Form form;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pupil_subject_marks",
            joinColumns = @JoinColumn(name = "pupils_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_mark_id")
    )
    private Set<SubjectMarks> subjectMarks;


    public Pupil(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Pupil(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }
}
