package com.andrei.race.repository;

import com.andrei.race.calculation.Racer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class RacerRepository {

    private static final String FORMAT = "yyyy-MM-dd_HH:mm:ss.SSS";
    private String filePathWithAbbreviations;
    private String filePathWithStart;
    private String filePathWithEnd;

    public RacerRepository(String filePathWithStart, String filePathWithEnd, String filePathWithAbbreviations) {
        this.filePathWithStart = filePathWithStart;
        this.filePathWithEnd = filePathWithEnd;
        this.filePathWithAbbreviations = filePathWithAbbreviations;
    }

    public List<Racer> getRacers() throws IOException {
        Map<String, LocalDateTime> startTime = getTime(filePathWithStart);
        Map<String, LocalDateTime> endTime = getTime(filePathWithEnd);
        return createRacers(startTime, endTime);
    }

    private Map<String, LocalDateTime> getTime(String path) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        if (classLoader.getResource(path) == null) {
            throw new FileNotFoundException(path);
        }
        File fileWithTime = new File((classLoader.getResource(path)).getFile());
        return Files.lines(Paths.get(fileWithTime.getPath())).collect(toMap(p -> p.substring(0, 3), t -> createTimeFromRacer(t)));
    }

    private LocalDateTime createTimeFromRacer(String time) {
        time = time.substring(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        return LocalDateTime.parse(time, formatter);
    }

    private List<Racer> createRacers(Map<String, LocalDateTime> startTime, Map<String, LocalDateTime> endTime) throws IOException {

        ClassLoader classLoader = this.getClass().getClassLoader();
        if (classLoader.getResource(filePathWithAbbreviations) == null) {
            throw new FileNotFoundException(filePathWithAbbreviations);
        }
        File fileAbbreviations = new File(classLoader.getResource(filePathWithAbbreviations).getFile());
        return Files.lines(Paths.get(fileAbbreviations.getPath()))
                .map(s -> createRacer(endTime.get(s.substring(0, 3)), startTime.get(s.substring(0, 3)), s))
                .collect(toList());
    }

    private Racer createRacer(LocalDateTime dateTimeEnd, LocalDateTime dateTimeStart, String abbreviations) {
        Duration lapTime = Duration.between(dateTimeStart, dateTimeEnd);
        String[] splitFile = abbreviations.split("_");
        return new Racer(splitFile[0], splitFile[1], splitFile[2], lapTime);
    }
}
