package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Director;

import java.util.List;

public interface DirectorService {
    public List<Director> getAllDirectors();

    public Director getDirectorById(Long id);

    public void saveDirector(Director director);

    public void editDirector(Director director);

    public void deleteDirectorById(Long id);
}
