package com.practice.bullsandcows.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
public class Turn extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @Column(nullable = false)
    private String guess;
    @Column(nullable = false)
    private Time time;
    @Column(nullable = false)
    private int bulls;
    @Column(nullable = false)
    private int cows;

    public Turn() {
    }

    public Turn(Game game, String guess, Time time, int bulls, int cows) {
        this.game = game;
        this.guess = guess;
        this.time = time;
        this.bulls = bulls;
        this.cows = cows;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
        return "Turn{" +
                "id=" + id +
                ", game=" + game +
                ", guess='" + guess + '\'' +
                ", time=" + time +
                ", bulls=" + bulls +
                ", cows=" + cows +
                '}';
    }
}
