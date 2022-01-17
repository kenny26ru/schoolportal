package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "nameForm")
//    String nameForm;

    @EmbeddedId
    @Column(name = "form_name")
    private Form formName;

    public Pupil(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

//    public void setNameForm(Form form) {
//        this.nameForm = form.getNumber() + form.getName();
//    }
}
