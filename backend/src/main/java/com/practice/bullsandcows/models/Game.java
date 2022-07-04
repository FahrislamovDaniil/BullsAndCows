package com.practice.bullsandcows.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;

@Entity
public class Game extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
    @Column(nullable = false)
    private String answer;
    @Column(nullable = false)
    private boolean unrestricted;
    @Column(name = "turn_restrict")
    private Integer turnRestrict;
    @Column(name = "time_restrict")
    private Time timeRestrict;
    @Column(nullable = false)
    private boolean finished;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<Turn> turns;

    public Game() {
    }

    public Game(Player player, String answer, boolean unrestricted,
                Integer turnRestrict, Time timeRestrict, boolean finished, List<Turn> turns) {
        this.player = player;
        this.answer = answer;
        this.unrestricted = unrestricted;
        this.turnRestrict = turnRestrict;
        this.timeRestrict = timeRestrict;
        this.finished = finished;
        this.turns = turns;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isUnrestricted() {
        return unrestricted;
    }

    public void setUnrestricted(boolean unrestricted) {
        this.unrestricted = unrestricted;
    }

    public Integer getTurnRestrict() {
        return turnRestrict;
    }

    public void setTurnRestrict(Integer turnRestrict) {
        this.turnRestrict = turnRestrict;
    }

    public Time getTimeRestrict() {
        return timeRestrict;
    }

    public void setTimeRestrict(Time timeRestrict) {
        this.timeRestrict = timeRestrict;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public void setTurns(List<Turn> turns) {
        this.turns = turns;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", player=" + player +
                ", answer='" + answer + '\'' +
                ", unrestricted=" + unrestricted +
                ", turnRestrict=" + turnRestrict +
                ", timeRestrict=" + timeRestrict +
                ", finished=" + finished +
                ", turns=" + turns +
                '}';
    }
}
