package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class MinMaxPlayer extends Player {

    public MinMaxPlayer(Game g, boolean p1) {
        super(g, p1);
        name = "MinMaxPlayer";
    }

    @Override
    public Action getMove(GameState state) {
        return minimaxAlphaBeta(state, Integer.MIN_VALUE, Integer.MAX_VALUE, true).action;
    }

    private SearchResult minimaxAlphaBeta(GameState state, int alpha, int beta, boolean maximizingPlayer) {
        if (game.endOfGame(state)) {
            return new SearchResult(evaluate(state), null);
        }

        int bestValue = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Action bestMove = null;

        for (Action action : game.getActions(state)) {
            GameState nextState = (GameState) game.doAction(state, action);
            SearchResult result = minimaxAlphaBeta(nextState, alpha, beta, !maximizingPlayer);

            if ((maximizingPlayer && result.value > bestValue) || (!maximizingPlayer && result.value < bestValue)) {
                bestValue = result.value;
                bestMove = action;
            }

            if (maximizingPlayer) {
                alpha = Math.max(alpha, bestValue);
            } else {
                beta = Math.min(beta, bestValue);
            }

            if (beta <= alpha) {
                break; // Élagage alpha-beta
            }
        }

        return new SearchResult(bestValue, bestMove);
    }

    private int evaluate(GameState state) {
        // Implémentez votre fonction d'évaluation ici
        // Elle doit retourner une valeur numérique représentant l'estimation de la qualité de l'état.
        return 0;
    }

    private static class SearchResult {
        int value;
        Action action;

        public SearchResult(int value, Action action) {
            this.value = value;
            this.action = action;
        }
    }
}
