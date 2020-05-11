package com.andrei.race;

import com.andrei.race.calculation.Racer;
import com.andrei.race.calculation.TopRacersFormatter;
import com.andrei.race.repository.RacerRepository;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        TopRacersFormatter topRacersFormatter = new TopRacersFormatter();
        RacerRepository racerRepository = new RacerRepository(
                "start.log",
                "end.log",
                "abbreviations.txt");

        List<Racer> racers = racerRepository.getRacers();
        String result = topRacersFormatter.format(racers, 15);
        System.out.println(result);
    }
}
