package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

import java.util.List;

public class MinMaxAlphaBetaPlayer extends Player {

    public int profondeurMax = 10;

    public MinMaxAlphaBetaPlayer(Game g, boolean p1) {
        super(g, p1);
        name = "MinMax";
    }

    @Override
    public Action getMove(GameState state) {
        System.out.println("AhHH");
        return getMinMaxMove(state);
    }

    public Action getMinMaxMove(GameState state) {
        return minMaxAlphaBeta(state).action;
    }

    public SearchResult minMaxAlphaBeta(GameState state) {
        if (player == 0) {
            return maxValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, profondeurMax);
        } else {
            return minValeur(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, profondeurMax);
        }
    }

    private SearchResult maxValeur(GameState state, double alpha, double beta, int profondeur) {
        //System.out.println("la profondeur = " + profondeur);
        if (game.endOfGame(state)) {
            return new SearchResult(state.getGameValue(), null);
        }

        double vmax = Double.NEGATIVE_INFINITY;
        Action cmax = null;

        List<Action> actions = game.getActions(state);

        if (profondeur>0){
            for (Action c : actions) {

                GameState nextState = (GameState) game.doAction(state, c);
                double value = minValeur(nextState, alpha, beta, profondeur--).value;
                if (value > vmax) {
                    vmax = value;
                    cmax = c;
                }

                alpha = Math.max(alpha, vmax);
                if (vmax >= beta) {
                    break;  // Coupure beta
                }
            }
        }

        return new SearchResult(vmax, cmax);
    }

    private SearchResult minValeur(GameState state, double alpha, double beta, int profondeur) {
        //System.out.println("la profondeur = " + profondeur);
        if (game.endOfGame(state)) {
            return new SearchResult(state.getGameValue(), null);
        }

        double vmin = Double.POSITIVE_INFINITY;
        Action cmin = null;

        List<Action> actions = game.getActions(state);
        if (profondeur>0) {

            for (Action c : actions) {
                //System.out.println("la profondeur = " + profondeur);
                GameState nextState = (GameState) game.doAction(state, c);
                double value = maxValeur(nextState, alpha, beta, profondeur--).value;

                if (value < vmin) {
                    vmin = value;
                    cmin = c;
                }

                beta = Math.min(beta, vmin);
                if (vmin <= alpha) {
                    break;  // Coupure alpha
                }
            }
        }

        return new SearchResult(vmin, cmin);
    }

    private static class SearchResult {
        double value;
        Action action;

        public SearchResult(double value, Action action) {
            this.value = value;
            this.action = action;
        }
    }

}
