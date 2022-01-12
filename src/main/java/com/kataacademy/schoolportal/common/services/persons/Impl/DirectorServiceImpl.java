package com.kataacademy.schoolportal.common.services.persons.Impl;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.repository.persons.DirectorRepository;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Override
    public Director getDirectorById(Long id) {

        return directorRepository.findById(id).orElseThrow();

    }

    @Override
    @Transactional
    public void saveDirector(Director director) {
        directorRepository.save(director);
    }

    @Override
    @Transactional
    public void editDirector(Director director) {

        directorRepository.save(director);

    }

    @Override
    @Transactional
    public void deleteDirectorById(Long id) {

        directorRepository.deleteById(id);

    }
}
