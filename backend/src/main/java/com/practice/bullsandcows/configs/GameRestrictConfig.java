package com.practice.bullsandcows.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.sql.Time;

@Component
@ConfigurationProperties(prefix = "game.restrict")
public class GameRestrictConfig {
    private boolean unrestricted;
    private Integer turn;
    private Time time;

    public boolean isUnrestricted() {
        return unrestricted;
    }

    public void setUnrestricted(boolean unrestricted) {
        this.unrestricted = unrestricted;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
