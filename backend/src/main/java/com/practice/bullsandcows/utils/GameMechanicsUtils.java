package com.practice.bullsandcows.utils;

import com.practice.bullsandcows.configs.GameRestrictConfig;
import com.practice.bullsandcows.models.Game;
import com.practice.bullsandcows.models.Turn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class GameMechanicsUtils {
    private final GameRestrictConfig gameRestrictConfig;

    @Autowired
    public GameMechanicsUtils(GameRestrictConfig gameRestrictConfig) {
        this.gameRestrictConfig = gameRestrictConfig;
    }

    public Game startNewGame(Game game) {
        int seqLen = 4;
        StringBuilder answer = new StringBuilder();
        List<Integer> digits = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Random rnd = new Random();

        for (int i = 0; i < seqLen; i++) {
            answer.append(digits.remove(rnd.nextInt(digits.size())));
        }

        game.setAnswer(answer.toString());
        game.setUnrestricted(gameRestrictConfig.isUnrestricted());
        game.setTurnRestrict(gameRestrictConfig.getTurn());
        game.setTimeRestrict(gameRestrictConfig.getTime());
        game.setFinished(false);
        game.setTurns(new ArrayList<Turn>());
        return game;
    }

    public Turn nextTurn(Game game, Turn turn) {
        String answer = game.getAnswer();
        String guess = turn.getGuess();
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < answer.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (answer.charAt(i) == guess.charAt(j)) {
                    if (i == j)
                        bulls++;
                    else
                        cows++;
                    break;
                }
            }
        }

        turn.setBulls(bulls);
        turn.setCows(cows);
        return turn;
    }
}
