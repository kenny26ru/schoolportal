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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping("/api/school-portal")
public class PupilProgressController {
    @Autowired
    PupilService pupilService;

    @Autowired
    FormService formService;

    @GetMapping(value = "/p_Id={pupilId}/performance/f_Id={formId}")
    @ApiOperation(
            value = "Вернуть оценки ученика по его предметам",
            response = ProgressDto.class
    )
    public ResponseEntity<ProgressDto> getPupilProgress(
            @PathVariable Long pupilId, @PathVariable Long formId) throws PersonNotFoundException {
        Pupil pupil = pupilService.getPupilById(pupilId);
        Form form = formService.getFormById(formId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(new ProgressDto(form, pupil));
    }

}
