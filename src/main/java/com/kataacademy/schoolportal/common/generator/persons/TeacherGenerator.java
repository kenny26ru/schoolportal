package com.kataacademy.schoolportal.common.generator.persons;

import com.kataacademy.schoolportal.common.models.persons.Teacher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeacherGenerator {

    Random generator = new Random();
    private static final Path fileMale;
    private static final Path fileFemale;
    private static final Path fileSurname;
    private static final Path filePatronMale;
    private static final Path filePatronFemale;

    static {
        fileMale = Path.of("data/MaleNames");
        fileFemale = Path.of("data/FemaleNames");
        fileSurname = Path.of("data/Surname");
        filePatronMale = Path.of("data/MalePatronymic");
        filePatronFemale = Path.of("data/FemalePatronymic");
    }

    public Teacher teacherGenerator() {
        boolean flag = generator.nextBoolean();
        String firstName = getFirstName(flag);
        String secondName = getSecondName(flag);
        String lastName = getLastName() + "а";
        String sex = flag ? "М" : "Ж";
        LocalDate birthday = randomDate();
        return new Teacher(firstName, secondName, lastName, sex, birthday);
    }

    private List<String> getFiles(Path path) {
        List<String> nameList = new ArrayList<>();
        try(Stream<String> lineStream = Files.lines(path)) {
            nameList = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    private String getFirstName(boolean sex) {
        return getString(sex, fileMale, fileFemale);
    }

    private String getSecondName(boolean sex) {
        return getString(sex, filePatronMale, filePatronFemale);
    }

    private String getString(boolean sex, Path maleFile, Path femaleFile) {
        List<String> fileList = sex ? getFiles(maleFile) : getFiles(femaleFile);
        return fileList.get(generator.nextInt(fileList.size()));
    }

    private String getLastName() {
        List<String> firstName = getFiles(fileSurname);
        return firstName.get(generator.nextInt(firstName.size()));
    }

    private LocalDate randomDate() {
        LocalDate startDate = LocalDate.of(1965, 1, 1);
        long start = startDate.toEpochDay();
        LocalDate endDate = LocalDate.now();
        long end = endDate.toEpochDay();
        long randomDate = ThreadLocalRandom.current().nextLong(start, end);
        return LocalDate.ofEpochDay(randomDate);
    }

}
