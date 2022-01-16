package com.kataacademy.schoolportal.common.services.persons.Impl;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.repository.persons.DirectorRepository;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.time.LocalTime.now;


@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Override
    public Director getDirectorById(Long id) {
//        return directorRepository.findById(id).orElseThrow();
        Director director = new Director(id,"Name", "SecondName", "LastName", "M", now());
        return director;
    }

    @Override
    public void saveDirector(Director director) {
        directorRepository.save(director);
    }

    @Override
    public void editDirector(Director director) {
        directorRepository.save(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }
}
