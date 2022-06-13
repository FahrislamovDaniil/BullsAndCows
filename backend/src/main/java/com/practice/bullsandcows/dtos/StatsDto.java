package com.practice.bullsandcows.dtos;

import java.sql.Time;

public class StatsDto {
    private Long gameId;
    private int turns;
    private Time time;

    public StatsDto() {
    }

    public StatsDto(Long gameId, int turns, Time time) {
        this.gameId = gameId;
        this.turns = turns;
        this.time = time;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "StatsDto{" +
                "gameId=" + gameId +
                ", turns=" + turns +
                ", time=" + time +
                '}';
    }
}
