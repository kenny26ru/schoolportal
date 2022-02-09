package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.schoolatribute.DayTimeTable;

import java.time.LocalDate;
import java.util.List;

public interface PupilService {
    public List<Pupil> getAllPupils();

    public Pupil getPupilById(Long id);

    public Pupil savePupil(Pupil pupil);

    public Pupil editPupil(Pupil pupil);

    public void deletePupilById(Long id);

}
