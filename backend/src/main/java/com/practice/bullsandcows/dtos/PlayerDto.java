package com.practice.bullsandcows.dtos;

import java.io.Serializable;

public class PlayerDto implements Serializable {
    private String nickname;

    public PlayerDto() {
    }

    public PlayerDto(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PlayerDto{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
