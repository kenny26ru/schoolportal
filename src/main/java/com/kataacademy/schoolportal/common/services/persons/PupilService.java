package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;

import java.util.List;

public interface PupilService {
    public List<Pupil> getAllPupils();

    public Pupil getPupilById(long id);

    public void savePupil(Pupil pupil);

    public void editPupil(Pupil pupil);

    public void deletePupil(long id);
}
