package com.kataacademy.schoolportal.common.mappers;

import com.kataacademy.schoolportal.common.models.enums.SchoolSubjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolSubjectsByClassGrade {

    Map<SchoolSubjects, List<Integer>> schoolSubjectsMap = new HashMap<>();
    List<Integer> classGrade = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    {
        schoolSubjectsMap.put(SchoolSubjects.CLEANLY_WRITING, classGrade.subList(0, 1));
        schoolSubjectsMap.put(SchoolSubjects.READING, classGrade.subList(0, 5));
        schoolSubjectsMap.put(SchoolSubjects.HANDY_CRAFT, classGrade.subList(0, 5));
        schoolSubjectsMap.put(SchoolSubjects.NATURAL_HISTORY, classGrade.subList(0, 6));
        schoolSubjectsMap.put(SchoolSubjects.MATHS, classGrade.subList(0, 7));
        schoolSubjectsMap.put(SchoolSubjects.VOCAL, classGrade.subList(0, 8));
        schoolSubjectsMap.put(SchoolSubjects.DRAWING, classGrade);
        schoolSubjectsMap.put(SchoolSubjects.PHYSICAL_EDUCATION, classGrade);
        schoolSubjectsMap.put(SchoolSubjects.RUSSIAN_LANGUAGE, classGrade);
        schoolSubjectsMap.put(SchoolSubjects.NATIVE_LANGUAGE, classGrade.subList(1, 11));
        schoolSubjectsMap.put(SchoolSubjects.FOREIGN_LANGUAGE, classGrade.subList(3, 11));
        schoolSubjectsMap.put(SchoolSubjects.THEOLOGY_AND_ETHICS, classGrade.subList(3, 5));
        schoolSubjectsMap.put(SchoolSubjects.CIVICS, classGrade.subList(4, 7));
        schoolSubjectsMap.put(SchoolSubjects.LOCAL_HISTORY, classGrade.subList(4, 7));
        schoolSubjectsMap.put(SchoolSubjects.HISTORY, classGrade.subList(4, 11));
        schoolSubjectsMap.put(SchoolSubjects.LITERATURE, classGrade.subList(4, 11));
        schoolSubjectsMap.put(SchoolSubjects.LIFE_SAFETY_FUNDAMENTALS, classGrade.subList(4, 11));
        schoolSubjectsMap.put(SchoolSubjects.TECHNOLOGY, classGrade.subList(4, 11));
        schoolSubjectsMap.put(SchoolSubjects.GEOGRAPHY, classGrade.subList(5, 10));
        schoolSubjectsMap.put(SchoolSubjects.BIOLOGY, classGrade.subList(5, 11));
        schoolSubjectsMap.put(SchoolSubjects.COMPUTER_SCIENCE, classGrade.subList(5, 11));
        schoolSubjectsMap.put(SchoolSubjects.SOCIAL_SCIENCE, classGrade.subList(5, 11));
        schoolSubjectsMap.put(SchoolSubjects.TECHNICAL_DRAWING, classGrade.subList(6, 8));
        schoolSubjectsMap.put(SchoolSubjects.ALGEBRA, classGrade.subList(6, 11));
        schoolSubjectsMap.put(SchoolSubjects.GEOMETRY, classGrade.subList(6, 11));
        schoolSubjectsMap.put(SchoolSubjects.PHYSICS, classGrade.subList(6, 11));
        schoolSubjectsMap.put(SchoolSubjects.CHEMISTRY, classGrade.subList(7, 11));
        schoolSubjectsMap.put(SchoolSubjects.NATURAL_SCIENCE, classGrade.subList(9, 11));
        schoolSubjectsMap.put(SchoolSubjects.BASIC_ECONOMICS, classGrade.subList(9, 11));
        schoolSubjectsMap.put(SchoolSubjects.JURISPRUDENCE, classGrade.subList(9, 11));
        schoolSubjectsMap.put(SchoolSubjects.PHILOSOPHY, classGrade.subList(9, 11));
        schoolSubjectsMap.put(SchoolSubjects.ECOLOGY, classGrade.subList(9, 11));
        schoolSubjectsMap.put(SchoolSubjects.ASTRONOMY, classGrade.subList(10, 11));
        schoolSubjectsMap.put(SchoolSubjects.BASIC_MILITARY_TRAINING, classGrade.subList(10, 11));
        schoolSubjectsMap.put(SchoolSubjects.WORLD_ART_CULTURE, classGrade);
        schoolSubjectsMap.put(SchoolSubjects.RHETORIC, classGrade);
    }
}
