package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import com.kataacademy.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.FORMAT_MESSAGE_DELETE;

@RestController
@RequestMapping("/api/teachers")
public class TeacherRestController {

    public static final String URL_TEACHER = "/api/teachers";
    public static final String PERSON = "учитель";

    @Autowired
    private TeacherService service;

    /**
     * Возвращает список всех учителей
     */
    @GetMapping("")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(ParamsRestController.APPLICATION_JSON_UTF8))
                .body(service.getAllTeachers());
    }

    /**
     * Возвращает учителя с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     */
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) throws PersonNotFoundException{
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(teacher);
    }

    /**
     * Создает нового учителя
     */
    @PostMapping("")
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.saveTeacher(teacher));
    }

    /**
     * Изменяет параметры учителя
     * или бросает исключение PersonNotFoundException
     */
    @PutMapping("")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody @Valid Teacher teacher) throws PersonNotFoundException {
        Long id = teacher.getId();
        Teacher oldTeacher = service.getTeacherById(id);
        if (oldTeacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.editTeacher(teacher));
    }

    /**
     * Удаляет учителя с заданым id
     * или бросает исключение PersonNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Long id) throws PersonNotFoundException {
        Teacher teacher = service.getTeacherById(id);
        if (teacher == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        service.deleteTeacherById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(String.format(FORMAT_MESSAGE_DELETE, PERSON, id));
    }
}
