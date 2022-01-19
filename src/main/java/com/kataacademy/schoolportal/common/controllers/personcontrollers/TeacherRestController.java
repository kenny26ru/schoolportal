package com.kataacademy.schoolportal.common.controllers.personcontrollers;


import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kataacademy.schoolportal.common.personcontrollers.exception.PersonNotFoundException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {

    private final TeacherService service;


    public TeacherRestController(TeacherService teacherService) {
        this.service = teacherService;
    }

    /**
     * Возвращает список всех учителей
     * @return список всех учителей
     */
    @GetMapping("")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> users = service.getAllTeachers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Возвращает учителя с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     * @param id идентификатор директора
     * @return учителя с заданым идентификатором
     */
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") Long id) {
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException("учитель", id);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    /**
     * Создает нового учителя
     * @param teacher
     * @return новая персона
     */
    @PostMapping("")
    public ResponseEntity<Teacher> addTeacher(@RequestBody @Valid Teacher teacher) {
        service.saveTeacher(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    /**
     * Изменяет параметры персоны
     * @param teacher персона с новыми значениями
     * @return персона с новыми значениями
     * @throws PersonNotFoundException персона не существует в БД
     */
    @PutMapping("")
    public ResponseEntity<Teacher> editTeacher(@RequestBody @Valid Teacher teacher) {
        Long id = teacher.getId();
        Teacher oldTeacher = service.getTeacherById(id);
        if (oldTeacher == null) {
            throw new PersonNotFoundException("учитель", id);
        }
        service.editTeacher(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    /**
     * Удаляет учителя с заданым id
     * @param id идентификатор персоны
     * @return сообщение об удалении персоны с заданным id
     * @throws PersonNotFoundException персона не существует в БД
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable("id") Long id) {
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException("учитель", id);
        }
        service.deleteTeacherById(id);
        return new ResponseEntity<>("Учитель с id=" + id + " удален", HttpStatus.OK);
    }
}
