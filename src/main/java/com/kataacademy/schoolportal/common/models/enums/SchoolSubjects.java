package com.kataacademy.schoolportal.common.models.enums;

public enum SchoolSubjects {

    CLEANLY_WRITING,
    READING,
    HANDY_CRAFT,
    NATURAL_HISTORY,
    MATHS,
    VOCAL,
    DRAWING,
    PHYSICAL_EDUCATION,
    RUSSIAN_LANGUAGE,
    NATIVE_LANGUAGE,
    FOREIGN_LANGUAGE,
    THEOLOGY_AND_ETHICS,
    CIVICS,
    LOCAL_HISTORY,
    HISTORY,
    LITERATURE,
    LIFE_SAFETY_FUNDAMENTALS,
    TECHNOLOGY,
    GEOGRAPHY,
    BIOLOGY,
    COMPUTER_SCIENCE,
    SOCIAL_SCIENCE,
    TECHNICAL_DRAWING,
    ALGEBRA,
    GEOMETRY,
    PHYSICS,
    CHEMISTRY,
    NATURAL_SCIENCE,
    BASIC_ECONOMICS,
    JURISPRUDENCE,
    PHILOSOPHY,
    ECOLOGY,
    ASTRONOMY,
    BASIC_MILITARY_TRAINING,
    WORLD_ART_CULTURE,
    RHETORIC,
    VALUE("value");

    private String value;

    SchoolSubjects(String value) {
        this.value = value;
    }

    SchoolSubjects() {

    }

    public String getValue() {
        return value;
    }
}
