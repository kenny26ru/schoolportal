package com.kataacademy.schoolportal.common.controllers.personcontrollers;

import com.kataacademy.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.schoolatribute.*;
import com.kataacademy.schoolportal.common.services.persons.PupilService;
import com.kataacademy.schoolportal.common.services.schooattribute.FormService;
import com.kataacademy.schoolportal.common.services.schooattribute.SchoolService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping("/api/school-portal")
public class ProgressRestController {
    @Autowired
    SchoolService schoolService;

    @Autowired
    PupilService pupilService;

    @Autowired
    FormService formService;

    @GetMapping(value = "/sc_Id={sId}/p_Id={pId}/performance/f_Id={fId}")
    @ApiOperation(
            value = "Вернуть прогресс ученика",
            response = ProgressDto.class
    )
    public ResponseEntity<ProgressDto> getPupilById(
            @PathVariable Long sId, @PathVariable Long pId, @PathVariable Long fId) throws PersonNotFoundException {
        Pupil pupil = pupilService.getPupilById(pId);
        School school = schoolService.getById(sId);
        Form form = formService.getFormById(fId);

        byte number = form.getNumber();
        List<SubjectMarks> subjectMarks = new ArrayList<>(pupil.getSubjectMarks());

        ProgressDto progressDto = new ProgressDto(number, subjectMarks);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(progressDto);
    }

}
