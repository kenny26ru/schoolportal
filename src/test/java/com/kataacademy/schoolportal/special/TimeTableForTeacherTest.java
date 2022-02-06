package com.kataacademy.schoolportal.special;


import org.junit.jupiter.api.*;


@DisplayName("Тестирование для учителя")
public class TimeTableForTeacherTest {

    private static int num = 1;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Тестирование начинается. num=" + num);
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("Выполняется перед каждым тестом. Тест №" + num);
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("Выполняется после каждого теста. Тест №" + num++);
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Тестирование завершено. num=" + num);
    }

    @Test
    void test1() {
        System.out.println("Это тест 1");
        Assertions.assertTrue(true);
    }

    @DisplayName("Повторяющийся тест")
    @RepeatedTest(value = 5, name = "{displayName} - проход {currentRepetition} из {totalRepetitions}")
    void test2(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Это тест 2. Попытка -> " + repetitionInfo.getCurrentRepetition());
        Assertions.assertEquals(1,1);
    }

    @Disabled
    @Test
    void test3() {
        Assertions.fail("Тест не прошел.");
    }

}
