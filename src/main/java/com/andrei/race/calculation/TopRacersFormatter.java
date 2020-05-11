package com.andrei.race.calculation;


import static java.util.Comparator.comparing;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class TopRacersFormatter {

    private static final String FORMAT_STRING_FROM_RACER = "%-2d. %-20s| %-30s|%d:%d.%d" + System.lineSeparator();

    public String format(List<Racer> racers, int lastPlace) {
        AtomicInteger place = new AtomicInteger();
        return racers.stream()
                .sorted(comparing(Racer::getBestTime))
                .map(racer -> formatRacer(racer, place.getAndIncrement(), lastPlace))
                .collect(joining(""));
    }

    private String formatRacer(Racer racer, int place, int lastPlace) {
        String formatRacer = "";
        if (place == lastPlace) {
            formatRacer += "-----------------------------------------------------------------" + System.lineSeparator();
        }
        place++;
        long minute = racer.getBestTime().getSeconds() / 60;
        long second = racer.getBestTime().getSeconds() - 60;
        long nano = racer.getBestTime().getNano() / 1000000;

        formatRacer += String.format(FORMAT_STRING_FROM_RACER, place, racer.getName(),
                racer.getTeam(), minute, second, nano);
        return formatRacer;
    }
}

