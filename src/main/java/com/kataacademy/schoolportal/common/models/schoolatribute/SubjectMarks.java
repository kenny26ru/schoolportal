package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject_mark")
public class SubjectMarks implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_subject")
    @Enumerated(EnumType.STRING)
    private SchoolSubjects schoolSubjects;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private List<Mark> marks;

    @Override
    public String toString() {
        return "SubjectMarks{" +
                "id=" + id +
                ", schoolSubjects=" + schoolSubjects +
                ", marks=" + marks.toString() +
                '}';
    }

}
