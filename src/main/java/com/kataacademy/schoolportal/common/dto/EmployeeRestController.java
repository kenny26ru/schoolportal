package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.controllers.personcontrollers.exception.PersonNotFoundException;
import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import com.kataacademy.schoolportal.common.services.schooattribute.SchoolService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    public static final String URL_EMPLOYEE = "/api/employees";
    public static final String PERSON = "работник школы";

    private final EmployeeService employeeService;
    private final SchoolService schoolService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, SchoolService schoolService) {
        this.employeeService = employeeService;
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Вернуть список работников определенной школы",
            notes = "Возвращает список работников определенной школы",
            response = EmployeeDto.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(
            @ApiParam(value = "Идентификатор школы, список работников которой нужно вернуть", required = true)
            @PathVariable Long id) throws PersonNotFoundException {

        School school = schoolService.getById(id);
        List<EmployeeDto> employeeDto = employeeService.findAllTeachersBySchool(school);

        if (school == null) {
            throw new PersonNotFoundException(PERSON, id);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(employeeDto);
    }
}
