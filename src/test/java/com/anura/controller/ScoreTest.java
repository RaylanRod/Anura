package com.anura.controller;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ScoreTest {

    @Test
    void testCorrect_UnitOf_Measure() {
        long startTime = Score.startTimer();
        long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        assertEquals(currentTime,startTime);
    }

    @Test
    void testCalcDuration_correct_calc() throws InterruptedException {
        long startTime = Score.startTimer();
        TimeUnit.SECONDS.sleep(5);
        long endTime = Score.endTimer();
        long testDuration = Score.calcDuration(startTime,endTime);
        assertEquals(5,testDuration);
    }
}