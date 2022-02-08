package com.kataacademy.schoolportal.personcontrollers.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kataacademy.schoolportal.common.generator.persons.PupilGenerator;
import com.kataacademy.schoolportal.common.generator.persons.TeacherGenerator;
import com.kataacademy.schoolportal.common.models.enums.Grade;
import com.kataacademy.schoolportal.common.models.enums.Profile;
import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;
import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.models.persons.Teacher;
import com.kataacademy.schoolportal.common.models.schoolatribute.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PupilProgressControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    private final PupilGenerator pupilGenerator = new PupilGenerator();
    private final Pupil pupil1 = pupilGenerator.generatePupilByGrade(Grade.ONE);
    private final Pupil pupil2 = pupilGenerator.generatePupilByGrade(Grade.ONE);
    private final Pupil pupil3 = pupilGenerator.generatePupilByGrade(Grade.ONE);
    private final Pupil pupil4 = pupilGenerator.generatePupilByGrade(Grade.ONE);

    private final Mark mark1 = new Mark(1, 4, LocalDate.of(2021, 4, 12));
    private final Mark mark2 = new Mark(2, 3, LocalDate.of(2021, 4, 13));
    private final Mark mark3 = new Mark(3, 5, LocalDate.of(2021, 4, 14));
    private final Mark mark4 = new Mark(4, 2, LocalDate.of(2021, 4, 11));
    private final Mark mark5 = new Mark(5, 4, LocalDate.of(2021, 4, 12));
    private final Mark mark6 = new Mark(6, 3, LocalDate.of(2021, 4, 13));
    private final Mark mark7 = new Mark(7, 3, LocalDate.of(2021, 4, 11));
    private final Mark mark8 = new Mark(8, 5, LocalDate.of(2021, 4, 15));
    private final Mark mark9 = new Mark(9, 4, LocalDate.of(2021, 4, 17));

    private final Teacher formTeacher = new Teacher(1L, "Катарина", "Филимонова", "Альбертовна", "Ж", LocalDate.of(1980, 4, 1));
    private final School school1 = new School("school1", 1000, "schoolAddress");

    private final Set<Pupil> pupilSet = new HashSet<>(List.of(pupil1, pupil2, pupil3, pupil4));
    private final Form form1 = new Form(1L, (byte) 3, "formName1", "form1", pupilSet, formTeacher, Profile.CHEMICAL_BIOLOGICAL.toString(), school1);

    private final List<Mark> listMarks1 = List.of(mark1, mark2);
    private final List<Mark> listMarks2 = List.of(mark3, mark4);
    private final List<Mark> listMarks3 = List.of(mark6, mark5);
    private final List<Mark> listMarks4 = List.of(mark7, mark8);
    private final List<Mark> listMarks5 = List.of(mark9);

    private final SubjectMarks subjectMarks1 = new SubjectMarks(1, SchoolSubjects.NATIVE_LANGUAGE, listMarks1);
    private final SubjectMarks subjectMarks2 = new SubjectMarks(2, SchoolSubjects.DRAWING, listMarks2);
    private final SubjectMarks subjectMarks3 = new SubjectMarks(3, SchoolSubjects.RUSSIAN_LANGUAGE, listMarks3);
    private final SubjectMarks subjectMarks4 = new SubjectMarks(4, SchoolSubjects.HISTORY, listMarks4);
    private final SubjectMarks subjectMarks5 = new SubjectMarks(5, SchoolSubjects.LITERATURE, listMarks5);

    private final List<SubjectMarks> listSubjectMarks1 = List.of(subjectMarks1, subjectMarks2, subjectMarks3);
    private final List<SubjectMarks> listSubjectMarks2 = List.of(subjectMarks4, subjectMarks5);

    @Test
    @DisplayName("Test GET JSON FROM PupilProgressDTO")
    void getPupilProgress() {

        String jsonString1 = (
                "{" +
                        "number:3, " +
                        "subjectMarks:[" +
                            "{id:1,schoolSubject:NATIVE_LANGUAGE," +
                                "marks:[" +
                                    "{id:1,mark:4,dayTime:[2021,4,12]}," +
                                    "{id:2,mark:3,dayTime:[2021,4,13]}" +
                            "]},"
                +
                            "{id:2,schoolSubject:DRAWING," +
                                "marks:[" +
                                    "{id:3,mark:5,dayTime:[2021,4,14]}," +
                                    "{id:4,mark:2,dayTime:[2021,4,11]}" +
                                "]},"
                +
                            "{id:3,schoolSubject:RUSSIAN_LANGUAGE," +
                                "marks:[" +
                                    "{id:6,mark:3,dayTime:[2021,4,13]}," +
                                    "{id:5,mark:4,dayTime:[2021,4,12]}" +
                                "]}"
                + "]}"
        ).replace(" ", "");

        String jsonString2 = (
                "{" +
                        "number:3, " +
                        "subjectMarks:[" +
                            "{id:4,schoolSubject:HISTORY," +
                                "marks:[" +
                                    "{id:7,mark:3,dayTime:[2021, 4, 11]}," +
                                    "{id:8,mark:5,dayTime:[2021, 4, 15]}" +
                                "]},"
                        +
                            "{id:5,schoolSubject:LITERATURE," +
                                "marks:[" +
                                    "{id:9,mark:4,dayTime:[2021, 4, 17]}" +
                                "]}"
                        + "]}"
        ).replace(" ", "");

        pupil1.setSubjectMarks(listSubjectMarks1);
        pupil2.setSubjectMarks(listSubjectMarks2);

        ProgressDto progressForPupil1 = new ProgressDto(form1, pupil1);
        ProgressDto progressForPupil2 = new ProgressDto(form1, pupil2);

        mapper.registerModule(new JavaTimeModule());

        String jsonFromProgressPupil1 = "";
        try {
            jsonFromProgressPupil1 = mapper.writeValueAsString(progressForPupil1)
                    .replace("\"", "")
                    .replace(" ", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(jsonString1, jsonFromProgressPupil1);

        String jsonFromProgressPupil2 = "";
        try {
            jsonFromProgressPupil2 = mapper.writeValueAsString(progressForPupil2)
                    .replace("\"", "")
                    .replace(" ", "");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(jsonString2, jsonFromProgressPupil2);
    }
}