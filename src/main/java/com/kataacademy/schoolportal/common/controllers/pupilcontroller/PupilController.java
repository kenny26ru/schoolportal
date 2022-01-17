package com.kataacademy.schoolportal.common.controllers.pupilcontroller;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pupil")
public class PupilController {

    private final PupilService pupilService;

    public PupilController(PupilService pupilService) {
        this.pupilService = pupilService;
    }

    @GetMapping()
    public ResponseEntity<List<Pupil>> pupils() {
        return ResponseEntity.ok(pupilService.getAllPupils());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pupil> getPupilById(@PathVariable("id") Long id) throws PersonNotFoundException {
        Pupil pupil = pupilService.getPupilById(id);
        if(pupil == null) {
            throw new PersonNotFoundException(id);
        }
        return ResponseEntity.ok(pupilService.getPupilById(id));
    }

    @PostMapping()
    public ResponseEntity<Pupil> createPupil(@RequestBody @Valid Pupil pupil) throws PersonForbiddenException,
            BadRequestException {
        pupilService.savePupil(pupil);
        return ResponseEntity.status(HttpStatus.CREATED).body(pupil);
    }

    @PutMapping()
    public ResponseEntity<Pupil> updatePupil(@RequestBody @Valid Pupil pupil) throws PersonNotFoundException,
            PersonForbiddenException {
       long id = pupil.getId();
       Pupil findPupil = pupilService.getPupilById(id);
       if(findPupil == null) {
           throw new PersonNotFoundException(id);
       }
       pupilService.editPupil(pupil);
       return new ResponseEntity<>(pupil, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePupil(@PathVariable("id") Long id) throws PersonNotFoundException,
            PersonForbiddenException {
        Pupil findPupil = pupilService.getPupilById(id);
        if(findPupil == null) {
            throw new PersonNotFoundException(id);
        }
        pupilService.deletePupil(id);
        return new ResponseEntity<>("OK. Директор с id=" + id + " удален", HttpStatus.OK);

    }

}
