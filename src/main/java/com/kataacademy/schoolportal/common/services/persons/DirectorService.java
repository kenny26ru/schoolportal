package com.kataacademy.schoolportal.common.services.persons;


import com.kataacademy.schoolportal.common.models.persons.Director;

public interface DirectorService {

    Director getDirectorById(Long id);

    void saveDirector(Director director);

    void editDirector(Director director);

    void deleteDirectorById(Long id);
}
