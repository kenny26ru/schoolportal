package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.kataacademy.schoolportal.common.dto.DirectorDTO;
import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    private final DirectorService directorService;

    @Autowired
    public DirectorRestController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("")
    @ApiOperation(
            value = "Вернуть список всех директоров",
            notes = "Возвращает список директоров с уникальными параметрами",
            response = Director.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<Director>> getAllDirectors() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(directorService.getAllDirectors());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Вернуть директора с заданным ID",
            notes = "Возвращает директора с заданым идентификатором или бросает исключение PersonNotFoundException",
            response = Director.class
    )
    public ResponseEntity<Director> getDirectorById(
            @ApiParam(value = "Идентификатор директора, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if (director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(director);
    }

    @GetMapping("/{id}/teachers")
    @ApiOperation(
            value = "Вернуть директора со списком учителей (только JSON список)",
            notes = "Возвращает директора со списком учителей, начиная с 5го класса",
            response = DirectorDTO.class
    )
    public ResponseEntity<DirectorDTO> getTeachersByDirectorIdFrom5Grade(
            @ApiParam(value = "Идентификатор директора, учителей которого нужно вернуть (с 5го класса)", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(directorService.getTeachersByDirectorIdFrom5Grade(id));
    }

    @PostMapping("")
    @ApiOperation(
            value = "Создать нового директора",
            notes = "Создает директора с заданными параметрами",
            response = Director.class
    )
    public ResponseEntity<Director> createDirector(@RequestBody @Valid Director director) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(directorService.saveDirector(director));
    }

    @PutMapping("")
    @ApiOperation(
            value = "Обновить существующего директора",
            notes = "Изменяет параметры директора или бросает исключение PersonNotFoundException",
            response = Director.class
    )
    public ResponseEntity<Director> updateDirector(@RequestBody @Valid Director director) throws PersonNotFoundException {
        Long id = director.getId();
        Director oldDirector = directorService.getDirectorById(id);
        if (oldDirector == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(directorService.editDirector(director));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Удалить существущего директора по ID",
            notes = "Удаляет директора с заданым id или бросает исключение PersonNotFoundException",
            response = String.class
    )
    public ResponseEntity<String> deleteDirector(
            @ApiParam(value = "Идентификатор директора, которого нужно найти", required = true)
            @PathVariable Long id) throws PersonNotFoundException {
        Director director = directorService.getDirectorById(id);
        if (director == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        directorService.deleteDirectorById(id);
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
