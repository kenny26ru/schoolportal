package com.kataacademy.schoolportal.common.queue;


import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.queue.exception.QueueException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PupilQueue {

    private static final Queue<Map<Integer, Pupil>> res = new LinkedList<>();
    private static final Map<Integer, Integer> valid = new HashMap<>();

    static {
        valid.put(1, 0);
        valid.put(2, 0);
        valid.put(3, 0);
        valid.put(4, 0);
        valid.put(5, 0);
        valid.put(6, 0);
        valid.put(7, 0);
        valid.put(8, 0);
        valid.put(9, 0);
        valid.put(10, 0);
        valid.put(11, 0);
    }

    public void putPupilInAQueue(Pupil pupil) {
        Map<Integer, Pupil> map = new HashMap<>();
        int classNumber = LocalDate.now().getYear() - pupil.getBirthday().getYear() - 7;
        if (classNumber > 11 || classNumber < 1) {
            throw new QueueException.BeyondAgeLimitsException();
        }
        map.put(classNumber, pupil);
        if (valid.get(classNumber) == 30) {
            throw new QueueException.NoPlaceInClassException(classNumber);
        }
        valid.merge(classNumber, valid.get(classNumber), (a, b) -> b + 1 );
        res.add(map);
    }

}
