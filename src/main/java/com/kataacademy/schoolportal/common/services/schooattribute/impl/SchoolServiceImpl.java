package com.kataacademy.schoolportal.common.services.schooattribute.impl;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import com.kataacademy.schoolportal.common.repository.schooattribute.SchoolRepository;
import com.kataacademy.schoolportal.common.services.schooattribute.SchoolService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;

    public SchoolServiceImpl(SchoolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<School> getSchools() {
        return repository.findAll();
    }

    @Override
    public School getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found " + id));
    }

    @Override
    public School save(School school) {
        repository.save(school);
        return school;
    }

    @Override
    public School edit(School school) {
        Long id = school.getId();

        if (id != null && repository.findById(id).isPresent()) {
            repository.save(school);
        } else {
            throw new EntityNotFoundException("school id is empty or not found in the repository, id=" + id);
        }
        return school;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
