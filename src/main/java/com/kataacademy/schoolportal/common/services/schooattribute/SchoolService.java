package com.kataacademy.schoolportal.common.services.schooattribute;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;

import java.util.List;

public interface SchoolService {

    public List<School> getSchools();

    public School save(School user);

    public School getById(Long id);

    public School edit(School school);

    public void delete(Long id);
}
