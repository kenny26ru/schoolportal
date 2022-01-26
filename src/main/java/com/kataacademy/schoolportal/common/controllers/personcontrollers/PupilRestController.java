package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
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
@RequestMapping("/api/pupils")
public class PupilRestController {

    public static final String URL_PUPIL = "/api/pupils";
    public static final String PERSON = "ученик";

    @Autowired
    private PupilService service;

    /**
     * Возвращает список всех учеников
     */
    @GetMapping()
    public ResponseEntity<List<Pupil>> getAllPupils() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.getAllPupils());
    }

    /**
     * Возвращает ученика с заданым идентификатором
     * или бросает исключение PersonNotFoundException
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pupil> getPupilById(@PathVariable Long id) throws PersonNotFoundException {
        Pupil pupil = service.getPupilById(id);
        if(pupil == null) {
            throw new PersonNotFoundException(PERSON,id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(pupil);
    }

    /**
     * Создает нового ученика
     */
    @PostMapping()
    public ResponseEntity<Pupil> createPupil(@RequestBody @Valid Pupil pupil) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.savePupil(pupil));
    }

    /**
     * Изменяет параметры ученика
     * или бросает исключение PersonNotFoundException
     */
    @PutMapping()
    public ResponseEntity<Pupil> updatePupil(@RequestBody @Valid Pupil pupil) throws PersonNotFoundException {
        Long id = pupil.getId();
        Pupil oldPupil = service.getPupilById(id);
        if(oldPupil == null) {
            throw new PersonNotFoundException(PERSON,id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(service.editPupil(pupil));
    }

    /**
     * Удаляет ученика с заданым id
     * или бросает исключение PersonNotFoundException
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePupil(@PathVariable Long id) throws PersonNotFoundException {
        Pupil pupil = service.getPupilById(id);
        if(pupil == null) {
            throw new PersonNotFoundException(PERSON,id);
        }
        service.deletePupilById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(String.format(FORMAT_MESSAGE_DELETE, PERSON, id));
    }

}
