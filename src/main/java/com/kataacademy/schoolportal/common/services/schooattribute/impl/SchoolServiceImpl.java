package com.kataacademy.schoolportal.common.services.schooattribute.impl;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import com.kataacademy.schoolportal.common.repository.schooattribute.SchoolRepository;

import com.kataacademy.schoolportal.common.services.schooattribute.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository repository;


    @Autowired
    public SchoolServiceImpl(SchoolRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<School> getSchools() {
        return repository.findAll();
    }

    @Override
    public School save(School school) {

        repository.save(school);
        return school;
    }

    @Override
    public School getSchoolById(int id) {
        return repository.findById(id).orElse(new School());
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
