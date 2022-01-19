package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/directors")
public class DirectorRestController {

    private static final String PERSON = "директор";

    //SonarLint выдал сообщение "Field injection is not recommended "
    @Autowired
    private DirectorService directorService;

    /**
     * Возвращает список всех директоров
     */
    @GetMapping("")
    public ResponseEntity<List<Director>> getAllDirectors() {
        List<Director> directors = directorService.getAllDirectors();
        return ResponseEntity.ok().body(directors);
    }

    /**
     * Возвращает директора с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Director> getOneDirector(@PathVariable("id") Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity.ok().body(director);
    }

    /**
     * Создает нового директора
     */
    @PostMapping("")
    public ResponseEntity<Director> createDirector(@RequestBody @Valid Director director) {
        directorService.saveDirector(director);
        return ResponseEntity.status(HttpStatus.CREATED).body(director);
    }

    /**
     * Изменяет параметры директора
     * или бросает исключение PersonNotFoundException
     */
    @PutMapping("")
    public ResponseEntity<Director> updateDirector(@RequestBody @Valid Director director) throws PersonNotFoundException {
        Long id = director.getId();
        Director oldDirector = directorService.getDirectorById(id);
        if(oldDirector == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        directorService.editDirector(director);
        return ResponseEntity.ok().body(director);
    }

    /**
     * Удаляет персону с заданым id
     * или бросает исключение PersonNotFoundException
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if(director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        directorService.deleteDirectorById(id);
        return ResponseEntity.ok().body("OK. Директор с id=" + id + " удален");
    }

}
