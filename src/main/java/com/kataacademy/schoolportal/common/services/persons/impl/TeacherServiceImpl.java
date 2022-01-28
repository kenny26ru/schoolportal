package com.kataacademy.schoolportal.common.services.persons.impl;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.repository.persons.TeacherRepository;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher editTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }
}
