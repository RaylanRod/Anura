package com.anura.controller;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Score {

    public static long startTimer() {
        long start = System.currentTimeMillis();
        long startTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(start);
        return startTimeSeconds;

    }

    public static long endTimer() {
        long finish = System.currentTimeMillis();
        long endTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(finish);
        return endTimeSeconds;
    }

    public static long calcDuration( long start, long finish) {
        long timeElapsed = finish - start;
        return timeElapsed;
    }

}