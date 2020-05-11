package com.andrei.race.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TopRacersFormatterTest {

    private TopRacersFormatter topRacersFormatter;
    private List<Racer> racers;

    @BeforeEach()
    public void setUp() {
        topRacersFormatter = new TopRacersFormatter();

        racers = new ArrayList<>();
        racers.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT5.115S")));
        racers.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT-7M-25.375S")));
        racers.add(new Racer("KML", "Kevin Magnussen", "LAAS FERRARI", Duration.parse("PT9M50.664S")));
    }

    @Test
    public void shouldBuildCorrectResult() {
        String expected =
                "1 . Lance Stroll        | WILLIAMS MERCEDES             |-7:-506.625" + System.lineSeparator() +
                        "2 . Marcus Ericsson     | SAUBER FERRARI                |0:-55.115" + System.lineSeparator() +
                        "-----------------------------------------------------------------" + System.lineSeparator() +
                        "3 . Kevin Magnussen     | LAAS FERRARI                  |9:530.664" + System.lineSeparator();

        String actual = topRacersFormatter.format(racers, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBuildCorrectResultWithoutLastPlace() {
        String expected =
                "1 . Lance Stroll        | WILLIAMS MERCEDES             |-7:-506.625" + System.lineSeparator() +
                        "2 . Marcus Ericsson     | SAUBER FERRARI                |0:-55.115" + System.lineSeparator() +
                        "3 . Kevin Magnussen     | LAAS FERRARI                  |9:530.664" + System.lineSeparator();

        String actual = topRacersFormatter.format(racers, 5);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldBuildCorrectResultWithoutAnyRacers() {
        String expected = "";
        List<Racer> racers = new ArrayList<>();

        String actual = topRacersFormatter.format(racers, 1);

        assertEquals(actual, expected);
    }
}
