package com.andrei.race.repository;

import com.andrei.race.calculation.Racer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class RacerRepositoryTest {

    @Test
    void shouldBeCorrectIfInformationFromFileReadCorrectly() throws IOException {
        RacerRepository racerRepository = new RacerRepository(
                "testStart.log",
                "testEnd.log",
                "testAbbreviations.txt");

        List<Racer> expected = new ArrayList<>();

        expected.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT5.115S")));
        expected.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT-7M-25.375S")));
        expected.add(new Racer("KML", "Kevin Magnussen", "LAAS FERRARI", Duration.parse("PT9M50.664S")));


        List<Racer> actual = racerRepository.getRacers();

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowFileNotFoundExceptionFromStartTime() {
        RacerRepository racerRepository = new RacerRepository(
                "wrongPath",
                "end.log",
                "abbreviations.txt");

        assertThrows(FileNotFoundException.class,
                racerRepository::getRacers);
    }

    @Test
    void shouldThrowFileNotFoundExceptionFromEndTime() {
        RacerRepository racerRepository = new RacerRepository(
                "start.log",
                "wrongPath",
                "abbreviations.txt");

        assertThrows(FileNotFoundException.class,
                racerRepository::getRacers);
    }

    @Test
    void shouldThrowFileNotFoundExceptionFromAbbreviations() {
        RacerRepository racerRepository = new RacerRepository(
                "start.log",
                "end.log",
                "wrongPath");

        assertThrows(FileNotFoundException.class,
                racerRepository::getRacers);
    }
}
