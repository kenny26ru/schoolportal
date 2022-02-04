package com.kataacademy.schoolportal.special;


import com.kataacademy.schoolportal.common.models.persons.Pupil;


public interface QueueService {

    Pupil getPupilFromQueue();

    Pupil addPupilIntoForm(Pupil pupil);
}
