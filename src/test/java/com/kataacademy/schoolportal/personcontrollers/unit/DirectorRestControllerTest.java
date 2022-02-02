package com.kataacademy.schoolportal.personcontrollers.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kataacademy.schoolportal.common.controllers.personcontrollers.DirectorRestController;
import com.kataacademy.schoolportal.common.models.persons.Director;
import com.kataacademy.schoolportal.common.services.persons.DirectorService;
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

import static com.kataacademy.schoolportal.common.controllers.personcontrollers.DirectorRestController.PERSON;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.DirectorRestController.URL_DIRECTOR;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.ParamsRestController.*;
import static com.kataacademy.schoolportal.common.controllers.personcontrollers.handler.PersonExceptionHandler.*;
import static com.kataacademy.schoolportal.personcontrollers.unit.PersonTestMethods.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(DirectorRestController.class)
class DirectorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private DirectorService service;

    private final Director director1 = new Director(1L, "Дмитрий", "Marsovich", "Plutonov", "М", LocalDate.of(1971,7,5));
    private final Director director2 = new Director(2L, "Jupiter", "Uranovich", "Saturnov", "М", LocalDate.of(1985,3,26));
    private final Director director3 = new Director(3L, "Venera", "Merkurievna", "Haumea", "Ж", LocalDate.of(1981,8,6));


    @DisplayName("Test Norma. GET=>/api/directors=>200.JSON(List<Director>)")
    @Test
    void GET_200_getAllDirectors() throws Exception {
        List<Director> directors = new ArrayList<>(Arrays.asList(director1, director2, director3));
        Mockito.when(service.getAllDirectors()).thenReturn(directors);
        assertTrue(GET_checkAllPersons(mockMvc, URL_DIRECTOR, directors, objectMapper));
    }

    @DisplayName("Test Norma. GET=>/api/directors/1=>200.JSON(Director)")
    @Test
    void GET_200_getOneDirector() throws Exception {
        Mockito.when(service.getDirectorById(1L)).thenReturn(director1);
        assertTrue(GET_checkOnePerson(mockMvc, URL_DIRECTOR + "/1", director1, objectMapper));
    }

    @DisplayName("Test Norma. POST.JSON(Director)=>/api/directors=>201.JSON(Director)")
    @Test
    void POST_201_createDirector() throws Exception {
        Mockito.when(service.saveDirector(Mockito.any())).thenReturn(director1);
        assertTrue(POST_checkOnePerson(mockMvc, URL_DIRECTOR, director1, objectMapper));
    }

    @DisplayName("Test Norma. PUT.JSON(Director)=>/api/directors=>200.JSON(Director)")
    @Test
    void PUT_200_updateDirector() throws Exception {
        Mockito.when(service.getDirectorById(Mockito.any())).thenReturn(director2);
        Mockito.when(service.editDirector(Mockito.any())).thenReturn(director2);
        assertTrue(PUT_checkOnePerson(mockMvc, URL_DIRECTOR, director2, objectMapper));
    }

    @DisplayName("Test Norma. DELETE=>/api/directors/3=>200.'Deleted'")
    @Test
    void DELETE_200_deleteDirector() throws Exception {
        Long id = 3L;
        Mockito.when(service.getDirectorById(Mockito.any())).thenReturn(director3);
        assertTrue(DELETE_checkOnePerson(mockMvc, URL_DIRECTOR + "/" + id, String.format(FORMAT_MESSAGE_DELETE, PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/directors/4=>404.'Not Found'")
    @Test
    void GET_404_getOneDirector() throws Exception {
        Long id = 4L;
        Mockito.when(service.getDirectorById(id)).thenReturn(null);
        assertTrue(GET_notFoundPerson(mockMvc, URL_DIRECTOR + "/" + id, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id)));
    }

    @DisplayName("Test Error. PUT.JSON(Director)=>/api/directors=>404.'Not Found'")
    @Test
    void PUT_404_updateDirector() throws Exception {
        Long id = 3L;
        Mockito.when(service.getDirectorById(id)).thenReturn(null);
        assertTrue(PUT_notFoundPerson(mockMvc, URL_DIRECTOR, director3, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id), objectMapper));
    }

    @DisplayName("Test Error. DELETE=>/api/directors/4=>404.'Not Found'")
    @Test
    void DELETE_404_deleteDirector() throws Exception {
        Long id = 4L;
        Mockito.when(service.getDirectorById(id)).thenReturn(null);
        assertTrue(DELETE_notFoundPerson(mockMvc, URL_DIRECTOR + "/" + id, String.format(FORMAT_MESSAGE_NOT_FOUND, PERSON, id)));
    }


    @DisplayName("Test Error. GET=>/api/directors/Q=>400.'Bad id'")
    @Test
    void GET_400_badUriParam() throws Exception {
        String id = "Q";
        assertTrue(GET_badUriParam(mockMvc, URL_DIRECTOR + "/" + id, String.format(FORMAT_MESSAGE_BAD_URI_PARAM, id)));
    }


    @DisplayName("Test Error. POST.JSON(badJsonFormat)=>/api/directors=>400.'Bad JSON format'")
    @Test
    void JSON_400_badJsonFormat() throws Exception {
        //после поля FirstName отсутствует символ ":"
        String badJsonFormat = "{\"firstName\" \"fn\", \"secondName\": \"sn\", \"lastName\": \"ln\", \"sex\": \"М\", \"birthday\": \"2000-02-02\"}";
        assertTrue(POST_badJsonFormat(mockMvc, URL_DIRECTOR, badJsonFormat, MESSAGE_BAD_JSON_FORMAT));
    }

    @DisplayName("Test Error. POST.JSON(badJsonData)=>/api/directors=>400.'Bad JSON data'")
    @Test
    void JSON_400_badJsonData() throws Exception {
        //полям *name заданы короткие строки, полю sex задан недопустимый символ
        String badJsonData = "{\"firstName\": \"a\", \"secondName\": \"b\", \"lastName\": \"c\", \"sex\": \"d\", \"birthday\": \"2000-02-02\"}";
        assertTrue(POST_badJsonData(mockMvc, URL_DIRECTOR, badJsonData, MESSAGE_BAD_JSON_DATA));
    }

    @DisplayName("Test Error. DELETE=>/api/directors=>500.'Internal Error'")
    @Test
    void DELETE_500_InternalError() throws Exception {
        mockMvc.perform(
                        delete("/api/directors/do500")
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("message").value("Внутренняя ошибка"))
                .andExpect(jsonPath("debug").value("ERROR: 500"));
    }
//    MvcResult result = .andReturn();
//    String str=result.getResolvedException().getMessage()
//    str=result.getResponse().getContentAsString()
//    assertTrue(str.equals("Bad credentials"));

}
