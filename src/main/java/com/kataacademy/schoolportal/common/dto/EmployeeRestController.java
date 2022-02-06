package com.kataacademy.schoolportal.common.dto;

import com.kataacademy.schoolportal.common.models.schoolatribute.School;
import io.swagger.annotations.ApiOperation;
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

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("")
    @ApiOperation(
            value = "Вернуть список всех работников школы",
            notes = "Возвращает список работников школы",
            response = EmployeeDto.class,
            responseContainer = "List"
    )
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(@RequestBody School school) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.valueOf(APPLICATION_JSON_UTF8))
                .body(employeeService.findAllTeachersBySchool(school));
    }
}
