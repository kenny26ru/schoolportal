package com.kataacademy.schoolportal.common.models.persons;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "pupils")
public class Pupil extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EmbeddedId
    @Column(name = "form_name")
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private FormName formName;

    public Pupil(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    @EqualsAndHashCode
    @ToString
    @Embeddable
    public class FormName implements Serializable {

        static final long serialVersionUID = 1L;

        @Getter
        @Setter
        private byte number;

        @Getter
        @Setter
        private String name;
    }
}
