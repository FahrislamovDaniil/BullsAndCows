package com.practice.bullsandcows.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Player extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nickname;
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    private List<Game> games;

    public Player() {
    }

    public Player(String nickname, List<Game> games) {
        this.nickname = nickname;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", games=" + games +
                '}';
    }
}
