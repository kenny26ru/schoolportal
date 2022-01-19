package com.kataacademy.schoolportal.common.generator.persons;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
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
        String secondName = getSurname();
        String lastName = getPatronymicMale();
        LocalDate birthday = getDate();

        if (sex.equalsIgnoreCase("Ж")) {
            secondName = getSurname() + "a";
            lastName = getPatronymicFemale();
        }

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
        List<String> surname = new ArrayList<>();

        try {
            File fileSurname = new ClassPathResource("data/Surname").getFile();
            try (Scanner scanner = new Scanner(fileSurname)) {
                while (scanner.hasNextLine()) {
                    surname.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(surname);
        return surname.get(0);

    }

    private String getPatronymicMale() {
        List<String> malePatronymic = new ArrayList<>();

        try {
            File filePatronMale = new ClassPathResource("data/MalePatronymic").getFile();
            try (Scanner scanner = new Scanner(filePatronMale)) {
                while (scanner.hasNextLine()) {
                    malePatronymic.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(malePatronymic);
        return malePatronymic.get(0);
    }

    private String getPatronymicFemale() {

        List<String> femalePatronymic = new ArrayList<>();
        try {
            File filePatronFemale = new ClassPathResource("data/FemalePatronymic").getFile();
            try (Scanner scanner = new Scanner(filePatronFemale)) {
                while (scanner.hasNextLine()) {
                    femalePatronymic.add(scanner.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(femalePatronymic);
        return femalePatronymic.get(0);

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
