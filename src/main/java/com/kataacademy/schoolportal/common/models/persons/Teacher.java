package com.kataacademy.schoolportal.common.models.persons;

import com.kataacademy.schoolportal.common.models.schoolatribute.Form;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
     * CascadeType - это то, что произойдет с подчиненной таблицей, при действиях с главной.
     * Конкретно в этом случае (CascadeType.ALL) будут выполнены все каскадные операции.
     *
     * Аннотация @JoinColumn нужна для настройки имени столбца в таблице teachers,
     * который сопоставляется с первичным ключом в таблице forms.
     * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id", referencedColumnName = "id")
    private Form teacherForm;




    @OneToMany(mappedBy = "teacher")
    private Set<Pupil> pupilSet;

    @ManyToOne
    private Director director;




    public Teacher(String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
    }

    public Teacher(Long id, String firstName, String secondName, String lastName, String sex, LocalDate birthday) {
        super(firstName, secondName, lastName, sex, birthday);
        this.id = id;
    }

}