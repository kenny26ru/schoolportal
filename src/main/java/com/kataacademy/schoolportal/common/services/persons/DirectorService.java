package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Director;

import java.util.List;

public interface DirectorService {
    public List<Director> getAllDirectors();

    public Director getDirectorById(Long id);

    public Director saveDirector(Director director);

    public Director editDirector(Director director);

    public void deleteDirectorById(Long id);
}
