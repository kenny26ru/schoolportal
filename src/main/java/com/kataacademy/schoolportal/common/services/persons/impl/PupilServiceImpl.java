package com.kataacademy.schoolportal.common.services.persons.impl;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.repository.persons.PupilRepository;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {

    @Autowired
    private PupilRepository pupilRepository;

    @Override
    public List<Pupil> getAllPupils() {
        return pupilRepository.findAll();
    }

    @Override
    public Pupil getPupilById(long id) {
        return pupilRepository.findById(id).orElseThrow();
    }

    @Override
    public void savePupil(Pupil pupil) {
        pupilRepository.save(pupil);
    }

    @Override
    public void editPupil(Pupil pupil) {
        pupilRepository.save(pupil);
    }

    @Override
    public void deletePupil(long id) {
        pupilRepository.deleteById(id);
    }
}
