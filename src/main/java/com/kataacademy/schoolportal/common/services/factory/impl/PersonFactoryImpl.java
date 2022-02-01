package com.kataacademy.schoolportal.common.services.factory.impl;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.services.factory.PersonFactory;
import com.kataacademy.schoolportal.common.models.persons.Person;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonFactoryImpl implements PersonFactory {

    @Autowired
    private DirectorService directorService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PupilService pupilService;

    @Override
    public Person savePersonByPersonRole(String roleName, Person person) {

        switch (roleName) {
            case "ROLE_DIRECTOR":

                return directorService.saveDirector((Director) person);

            case "ROLE_HEAD_TEACHER":

            case "ROLE_TEACHER":

                return teacherService.saveTeacher((Teacher) person);

            default:

                return pupilService.savePupil((Pupil) person);
        }
    }
}
