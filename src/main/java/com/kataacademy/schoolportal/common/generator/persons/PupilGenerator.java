package com.kataacademy.schoolportal.common.generator.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class PupilGenerator {
    Random generator = new Random();

    public Pupil generatePupil() {
        Map<String, String> mapName = getName();
        Object[] names = mapName.keySet().toArray();
        String firstName = names[generator.nextInt(names.length)].toString();
        String sex = mapName.get(firstName);
        String secondName = sex.equalsIgnoreCase("М") ? getSurname() : getSurname() + "a";
        String lastName = sex.equalsIgnoreCase("М") ? getPatronymicMale() : getPatronymicFemale();
        LocalDate birthday = getDate();

        return new Pupil(firstName, secondName, lastName, sex, birthday);
    }

    private Map<String, String> getName() {
        File fileMale;
        File fileFemale;
        Map<String, String> mapName = new HashMap<>();
        try {
            fileMale = new ClassPathResource("data/MaleNames").getFile();
            fileFemale = new ClassPathResource("data/FemaleNames").getFile();

            try (Scanner scannerMale = new Scanner(fileMale)) {
                while (scannerMale.hasNextLine()) {
                    mapName.put(scannerMale.nextLine(), "М");
                }
            }

            try (Scanner scanner = new Scanner(fileFemale)) {
                while (scanner.hasNextLine()) {
                    mapName.put(scanner.nextLine(), "Ж");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mapName;
    }

    private String getSurname() {
        List<String> surnameList = new ArrayList<>();

        try {
            File fileSurname = new ClassPathResource("data/Surname").getFile();
            try (Scanner scanner = new Scanner(fileSurname)) {
                while (scanner.hasNextLine()) {
                    surnameList.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return surnameList.get(generator.nextInt(surnameList.size()));
    }

    private String getPatronymicMale() {
        List<String> malePatronymicList = new ArrayList<>();

        try {
            File filePatronMale = new ClassPathResource("data/MalePatronymic").getFile();
            try (Scanner scanner = new Scanner(filePatronMale)) {
                while (scanner.hasNextLine()) {
                    malePatronymicList.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return malePatronymicList.get(generator.nextInt(malePatronymicList.size()));
    }

    private String getPatronymicFemale() {

        List<String> femalePatronymicList = new ArrayList<>();
        try {
            File filePatronFemale = new ClassPathResource("data/FemalePatronymic").getFile();
            try (Scanner scanner = new Scanner(filePatronFemale)) {
                while (scanner.hasNextLine()) {
                    femalePatronymicList.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return femalePatronymicList.get(generator.nextInt(femalePatronymicList.size()));

    }

    private LocalDate getDate() {
        LocalDate dateNow = LocalDate.now();
        LocalDate dateStart = dateNow.minusYears(18);
        LocalDate dateEnd = dateNow.minusYears(6);

        return randomDate(dateStart, dateEnd);
    }

    private LocalDate randomDate(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }

}
