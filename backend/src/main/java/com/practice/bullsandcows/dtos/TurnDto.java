package com.practice.bullsandcows.dtos;

import java.io.Serializable;
import java.sql.Time;

public class TurnDto implements Serializable {
    private Long game;
    private String guess;
    private Time time;
    private int bulls;
    private int cows;

    public TurnDto() {
    }

    public TurnDto(Long game, String guess, Time time, int bulls, int cows) {
        this.game = game;
        this.guess = guess;
        this.time = time;
        this.bulls = bulls;
        this.cows = cows;
    }

    public Long getGame() {
        return game;
    }

    public void setGame(Long game) {
        this.game = game;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getBulls() {
        return bulls;
    }

    public void setBulls(int bulls) {
        this.bulls = bulls;
    }

    public int getCows() {
        return cows;
    }

    public void setCows(int cows) {
        this.cows = cows;
    }

    @Override
    public String toString() {
        return "TurnDto{" +
                "game=" + game +
                ", guess='" + guess + '\'' +
                ", time=" + time +
                ", bulls=" + bulls +
                ", cows=" + cows +
                '}';
    }
}
