package com.andrei.race.calculation;

import java.time.Duration;
import java.util.Objects;

public class Racer {

    private String abbreviation;
    private Duration bestTime;
    private String name;
    private String team;


    public Racer(String abbreviation, String name, String team, Duration bestTime) {
        this.abbreviation = abbreviation;
        this.bestTime = bestTime;
        this.team = team;
        this.name = name;

    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Duration getBestTime() {
        return bestTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return abbreviation.equals(racer.abbreviation) &&
                bestTime.equals(racer.bestTime) &&
                name.equals(racer.name) &&
                team.equals(racer.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(abbreviation, bestTime, name, team);
    }
}

