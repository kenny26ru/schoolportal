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
    public Pupil getPupilById(Long id) {
        return pupilRepository.findById(id).orElseThrow();
    }

    @Override
    public Pupil savePupil(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    @Override
    public Pupil editPupil(Pupil pupil) {
        return pupilRepository.save(pupil);
    }

    @Override
    public void deletePupilById(Long id) {
        pupilRepository.deleteById(id);
    }
}
