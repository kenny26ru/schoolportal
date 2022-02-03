package com.kataacademy.schoolportal.common.services.persons.impl;

import com.kataacademy.schoolportal.common.dto.DirectorDTO;
import com.kataacademy.schoolportal.common.mappers.DirectorMapper;
import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.repository.persons.DirectorRepository;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImpl implements DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public DirectorServiceImpl(DirectorRepository directorRepository, DirectorMapper directorMapper) {
        this.directorRepository = directorRepository;
        this.directorMapper = directorMapper;
    }

    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Override
    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    @Override
    public Director saveDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public Director editDirector(Director director) {
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }

    // DTO
    public DirectorDTO createDirector(DirectorDTO directorDTO) {
        Director director = new Director();
        director.setFirstName(directorDTO.getFirstName());
        director.setSecondName(directorDTO.getSecondName());
        director.setLastName(directorDTO.getLastName());
        director.setSex(directorDTO.getSex());
        director.setBirthday(directorDTO.getBirthday());

        director.setTeacherSet(directorDTO.getTeacherSet());

        Director savedDirector = directorRepository.saveAndFlush(director);
        return directorMapper.directorToDirectorDTO(savedDirector);
    }
}
