package com.kataacademy.schoolportal.common.queue;

import com.kataacademy.schoolportal.common.models.persons.Pupil;
import com.kataacademy.schoolportal.common.queue.exception.QueueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class PupilQueueTest {

    PupilQueue expected = new PupilQueue();

    @Test
    void multithreadedTesting() {
        List<Pupil> pupils = new ArrayList<>();
        List<Pupil> pupils1 = new ArrayList<>();
        for (int i = 0; i <= 122; i++) {
            Pupil pupil = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(9));
            Pupil pupil1 = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(8));
            pupils.add(pupil);
            pupils1.add(pupil1);
        }
        MyThead myThead = new MyThead(pupils, expected, 1);
        MyThead myThead1 = new MyThead(pupils, expected, 2);
        MyThead myThead2 = new MyThead(pupils1, expected, 3);
        MyThead myThead3 = new MyThead(pupils1, expected, 4);

        myThead.start();
        myThead1.start();
        myThead2.start();
        myThead3.start();
        try {
            myThead.join();
            myThead1.join();
            myThead2.join();
            myThead3.join();
        } catch (InterruptedException ignored) {
        }
        Assertions.assertEquals(240, expected.getQueue().size());
    }

    @Test
    void getNoPlaceInClassException() {
        for (int i = 0; i <= 123; i++) {
            Pupil pupil = new Pupil("asd" + i, "asdaf" + i, "asdfgha" + i, "M", LocalDate.now().minusYears(11));
            try {
                expected.putPupilInAQueue(pupil);
            } catch (QueueException.NoPlaceInClassException | QueueException.BeyondAgeLimitsException exception) {
                Assertions.assertEquals(QueueException.NoPlaceInClassException.class, exception.getClass());
            }
        }
        Assertions.assertEquals(120, expected.getQueue().size());
    }

    @Test
    void getBeyondAgeLimitsException() {
        Pupil pupil = new Pupil("asd", "asdaf", "asdfgha", "M", LocalDate.now().minusYears(7));
        try {
            expected.putPupilInAQueue(pupil);
        } catch (QueueException.NoPlaceInClassException | QueueException.BeyondAgeLimitsException exception) {
            Assertions.assertEquals(QueueException.BeyondAgeLimitsException.class, exception.getClass());
        }
        Assertions.assertEquals(0, expected.getQueue().size());
    }

    class MyThead extends Thread {

        List<Pupil> pupils;
        PupilQueue expected;
        int number;
        int a;

        public MyThead(List<Pupil> pupils, PupilQueue expected, int number) {
            this.pupils = pupils;
            this.expected = expected;
            this.number = number;
        }


        @Override
        public void run() {
            for (Pupil pupil : pupils) {
                try {
                    expected.putPupilInAQueue(pupil);
                } catch (QueueException.NoPlaceInClassException ignored) {
                }
            }
        }
    }
}
