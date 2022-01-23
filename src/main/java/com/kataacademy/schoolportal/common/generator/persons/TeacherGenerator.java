package com.kataacademy.schoolportal.common.generator.persons;

import com.kataacademy.schoolportal.common.models.persons.Teacher;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TeacherGenerator {

    Random generator = new Random();
    private static File fileMale;
    private static File fileFemale;
    private static File fileSurname;
    private static File filePatronMale;
    private static File filePatronFemale;

    static {
        try {
            fileMale = new ClassPathResource("data/MaleNames").getFile();
            fileFemale = new ClassPathResource("data/FemaleNames").getFile();
            fileSurname = new ClassPathResource("data/Surname").getFile();
            filePatronMale = new ClassPathResource("data/MalePatronymic").getFile();
            filePatronFemale = new ClassPathResource("data/FemalePatronymic").getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Teacher teacherGenerator() {
        if(generator.nextBoolean()) {
            List<String> fName = Arrays.asList(fileMale.list());
            List<String> sName = Arrays.asList(filePatronMale.list());
            List<String> lName = Arrays.asList(fileSurname.list());
            String firstName = fName.get(generator.nextInt(fName.size()));
            String secondName = sName.get(generator.nextInt(sName.size()));
            String lastName = lName.get(generator.nextInt(lName.size()));
            String sex = "М";
        }
    }

    private LocalDate randomDate() {
        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

}
