package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

import java.util.ArrayList;

public class MinMaxPlayer extends Player {

    private static final int INFINITY = Integer.MAX_VALUE;

    public MinMaxPlayer(Game g, boolean p1) {
        super(g, p1);
        name = "MinMaxPlayer";
    }

    @Override
    public Action getMove(GameState state) {
        int currentPlayer = (player == PLAYER1) ? 1 : 2;
        return minimax((GameState) state, currentPlayer, Integer.MIN_VALUE, Integer.MAX_VALUE).getSecond();
    }

    private Pair<Integer, Action> minimax(GameState state, int player, int alpha, int beta) {
        if (game.endOfGame(state)) {
            return new Pair<>(evaluate(state), null);
        }

        int currentPlayer = (player == 1) ? PLAYER1 : PLAYER2;
        Action bestMove = null;

        if (currentPlayer == player) {
            int maxValue = Integer.MIN_VALUE;
            for (Action action : game.getActions(state)) {
                GameState nextState = (GameState) game.doAction(state, action);
                int value = minimax(nextState, 3 - player, alpha, beta).getFirst();
                if (value > maxValue) {
                    maxValue = value;
                    bestMove = action;
                }
                alpha = Math.max(alpha, maxValue);
                if (maxValue >= beta) {
                    break;
                }
            }
            return new Pair<>(maxValue, bestMove);
        } else {
            int minValue = Integer.MAX_VALUE;
            for (Action action : game.getActions(state)) {
                GameState nextState = (GameState) game.doAction(state, action);
                int value = minimax(nextState, 3 - player, alpha, beta).getFirst();
                if (value < minValue) {
                    minValue = value;
                    bestMove = action;
                }
                beta = Math.min(beta, minValue);
                if (minValue <= alpha) {
                    break;
                }
            }
            return new Pair<>(minValue, bestMove);
        }
    }

    // Méthode d'évaluation spécifique à MinMaxPlayer
    private int evaluate(GameState state) {
        // Implémente la logique d'évaluation spécifique à MinMaxPlayer
        return 0;
    }

    private static class Pair<T, U> {
        private final T first;
        private final U second;

        private Pair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }
}
