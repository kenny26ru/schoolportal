package com.kataacademy.schoolportal.common.services.persons.impl;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.repository.persons.PupilRepository;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PupilServiceImpl implements PupilService {
    private final PupilRepository pupilRepository;

    @Autowired
    public PupilServiceImpl(PupilRepository pupilRepository) {
        this.pupilRepository = pupilRepository;
    }

    @Override
    public List<Pupil> getAllPupils() {
        return pupilRepository.findAll();
    }

    @Override
    public Pupil getPupilById(int id) {
        return pupilRepository.getById(id);
    }

    @Override
    public void savePupil(Pupil pupil) {
        pupilRepository.save(pupil);
    }

    @Override
    public void deletePupil(int id) {
        pupilRepository.deleteById(id);
    }
}
