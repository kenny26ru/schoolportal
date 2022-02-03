package com.kataacademy.schoolportal.personcontrollers.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kataacademy.schoolportal.common.controllers.personcontrollers.TeacherRestController;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.services.persons.TeacherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.TeacherRestController.PERSON;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.TeacherRestController.URL_TEACHER;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.*;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.handler.PersonExceptionHandler.*;
import static com.kataacademy.schoolportal.personcontrollers.unit.PersonTestMethods.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WebMvcTest(TeacherRestController.class)
class TeacherRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TeacherService service;

    private final Teacher teacher1 = new Teacher(1L, "Дмитрий", "Kivanovich", "Bobov", "М", LocalDate.of(1971,7,5));
    private final Teacher teacher2 = new Teacher(2L, "Loba", "Ukropovich", "Schpinatov", "М", LocalDate.of(1985,3,26));
    private final Teacher teacher3 = new Teacher(3L, "Cykkini", "Turnepsova", "Yams", "Ж", LocalDate.of(1981,8,6));


    @DisplayName("Test Norma. GET=>/api/teachers=>200.JSON(List<Teacher>)")
    @Test
    void GET_200_getAllTeachers() throws Exception {
        List<Teacher> teachers = new ArrayList<>(Arrays.asList(teacher1, teacher2, teacher3));
        Mockito.when(service.getAllTeachers()).thenReturn(teachers);
        assertTrue(GET_checkAllPersons(mockMvc, URL_TEACHER, teachers, objectMapper));
    }

    @DisplayName("Test Norma. GET=>/api/teachers/1=>200.JSON(Teacher)")
    @Test
    void GET_200_getOneTeacher() throws Exception {
        Mockito.when(service.getTeacherById(1L)).thenReturn(teacher1);
        assertTrue(GET_checkOnePerson(mockMvc, URL_TEACHER + "/1", teacher1, objectMapper));
    }

    @DisplayName("Test Norma. POST.JSON(Teacher)=>/api/teachers=>201.JSON(Teacher)")
    @Test
    void POST_201_createTeacher() throws Exception {
        Mockito.when(service.saveTeacher(Mockito.any())).thenReturn(teacher1);
        assertTrue(POST_checkOnePerson(mockMvc, URL_TEACHER, teacher1, objectMapper));
    }

    @DisplayName("Test Norma. PUT.JSON(Teacher)=>/api/teachers=>200.JSON(Teacher)")
    @Test
    void PUT_200_updateTeacher() throws Exception {
        Mockito.when(service.getTeacherById(Mockito.any())).thenReturn(teacher2);
        Mockito.when(service.editTeacher(Mockito.any())).thenReturn(teacher2);
        assertTrue(PUT_checkOnePerson(mockMvc, URL_TEACHER, teacher2, objectMapper));
    }

    @DisplayName("Test Norma. DELETE=>/api/teachers/3=>200.'Deleted'")
    @Test
    void DELETE_200_deleteTeacher() throws Exception {
        Long id = 3L;
        Mockito.when(service.getTeacherById(Mockito.any())).thenReturn(teacher3);
        assertTrue(DELETE_checkOnePerson(mockMvc, URL_TEACHER + "/" + id, String.format(FORMAT_MESSAGE_DELETE, PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/teachers/4=>404.'Not Found'")
    @Test
    void GET_404_getOneTeacher() throws Exception {
        Long id = 4L;
        Mockito.when(service.getTeacherById(id)).thenReturn(null);
        assertTrue(GET_notFoundPerson(mockMvc, URL_TEACHER + "/" + id, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id)));
    }

    @DisplayName("Test Error. PUT.JSON(Teacher)=>/api/teachers=>404.'Not Found'")
    @Test
    void PUT_404_updateTeacher() throws Exception {
        Long id = 3L;
        Mockito.when(service.getTeacherById(id)).thenReturn(null);
        assertTrue(PUT_notFoundPerson(mockMvc, URL_TEACHER, teacher3, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id), objectMapper));
    }

    @DisplayName("Test Error. DELETE=>/api/teachers/4=>404.'Not Found'")
    @Test
    void DELETE_404_deleteTeacher() throws Exception {
        Long id = 4L;
        Mockito.when(service.getTeacherById(id)).thenReturn(null);
        assertTrue(DELETE_notFoundPerson(mockMvc, URL_TEACHER + "/" + id, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/teachers/Q=>400.'Bad id'")
    @Test
    void GET_400_badUriParam() throws Exception {
        String id = "Q";
        assertTrue(GET_badUriParam(mockMvc, URL_TEACHER + "/" + id, String.format(FORMAT_MESSAGE_BAD_URI_PARAM, id)));
    }


    @DisplayName("Test Error. POST.JSON(badJsonFormat)=>/api/teachers=>400.'Bad JSON format'")
    @Test
    void JSON_400_badJsonFormat() throws Exception {
        //после поля FirstName отсутствует символ ":" 
        String badJsonFormat = "{\"firstName\" \"fn\", \"secondName\": \"sn\", \"lastName\": \"ln\", \"sex\": \"М\", \"birthday\": \"2000-02-02\"}";
        assertTrue(POST_badJsonFormat(mockMvc, URL_TEACHER, badJsonFormat, MESSAGE_BAD_JSON_FORMAT));
    }

    @DisplayName("Test Error. POST.JSON(badJsonData)=>/api/teachers=>400.'Bad JSON data'")
    @Test
    void JSON_400_badJsonData() throws Exception {
        //полям *name заданы короткие строки, полю sex задан недопустимый символ
        String badJsonData = "{\"firstName\": \"a\", \"secondName\": \"b\", \"lastName\": \"c\", \"sex\": \"d\", \"birthday\": \"2000-02-02\"}";
        assertTrue(POST_badJsonData(mockMvc, URL_TEACHER, badJsonData, MESSAGE_BAD_JSON_DATA));
    }

}
