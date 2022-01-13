package com.kataacademy.schoolportal.common.services.persons;

import com.kataacademy.schoolportal.common.models.persons.Teacher;

public interface TeacherService {
    Iterable<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    void saveTeacher(Teacher teacher);
    void editTeacher(Teacher teacher);
    void deleteTeacherById(Long id);
}
