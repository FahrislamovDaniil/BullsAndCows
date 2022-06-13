package com.practice.bullsandcows.dtos;

import java.io.Serializable;
import java.sql.Time;

public class GameDto implements Serializable {
    private Long id;
    private String player;
    private boolean unrestricted;
    private int turnRestrict;
    private Time timeRestrict;

    public GameDto() {
    }

    public GameDto(Long id, String player, boolean unrestricted, int turnRestrict, Time timeRestrict) {
        this.id = id;
        this.player = player;
        this.unrestricted = unrestricted;
        this.turnRestrict = turnRestrict;
        this.timeRestrict = timeRestrict;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public boolean isUnrestricted() {
        return unrestricted;
    }

    public void setUnrestricted(boolean unrestricted) {
        this.unrestricted = unrestricted;
    }

    public int getTurnRestrict() {
        return turnRestrict;
    }

    public void setTurnRestrict(int turnRestrict) {
        this.turnRestrict = turnRestrict;
    }

    public Time getTimeRestrict() {
        return timeRestrict;
    }

    public void setTimeRestrict(Time timeRestrict) {
        this.timeRestrict = timeRestrict;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", player='" + player + '\'' +
                ", unrestricted=" + unrestricted +
                ", turnRestrict=" + turnRestrict +
                ", timeRestrict=" + timeRestrict +
                '}';
    }
}
