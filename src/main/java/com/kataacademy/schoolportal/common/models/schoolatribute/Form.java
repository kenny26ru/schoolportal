package com.kataacademy.schoolportal.common.models.schoolatribute;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Form {

    private byte number;
    private String name;
    private String formName;

    @OneToMany(mappedBy = "formName")
    private List<Pupil> pupilList;

    private Teacher formTeacher;
    private String profile;

}
