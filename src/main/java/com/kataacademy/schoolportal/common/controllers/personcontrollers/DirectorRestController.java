package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
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
@RequestMapping("/api/directors")
public class DirectorRestController {

    public static final String URL_DIRECTOR = "/api/directors";
    public static final String PERSON = "директор";

    @Autowired
    private DirectorService service;

    /**
     * Возвращает список всех директоров
     */
    @GetMapping("")
    public ResponseEntity<List<Director>> getAllDirectors() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.getAllDirectors());
    }

    /**
     * Возвращает директора с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Director> getDirectorById(@PathVariable Long id) throws PersonNotFoundException {
        Director director = service.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(director);
    }

    /**
     * Создает нового директора
     */
    @PostMapping("")
    public ResponseEntity<Director> createDirector(@RequestBody @Valid Director director) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.saveDirector(director));
    }

    /**
     * Изменяет параметры директора
     * или бросает исключение PersonNotFoundException
     */
    @PutMapping("")
    public ResponseEntity<Director> updateDirector(@RequestBody @Valid Director director) throws PersonNotFoundException {
        Long id = director.getId();
        Director oldDirector = service.getDirectorById(id);
        if(oldDirector == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.editDirector(director));
    }

    /**
     * Удаляет директора с заданым id
     * или бросает исключение PersonNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) throws PersonNotFoundException {
        Director director = service.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        service.deleteDirectorById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(String.format(FORMAT_MESSAGE_DELETE, PERSON, id));
    }

    @DeleteMapping("/do500")
    public ResponseEntity<String> internalError() {
        throw new ArrayIndexOutOfBoundsException("ERROR: 500");
    }
}
