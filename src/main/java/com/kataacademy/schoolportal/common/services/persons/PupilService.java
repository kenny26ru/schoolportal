package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;

import java.util.List;

public interface PupilService {
    public List<Pupil> getAllPupils();

    public Pupil getPupilById(int id);

    public void savePupil(Pupil pupil);

    public void deletePupil(int id);
}
