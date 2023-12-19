package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.recherche.Problem;
import ia.framework.recherche.SearchProblem;

import java.util.ArrayList;

public class RushHour extends SearchProblem {

    //Actions possibles : (Avancer, Reculer)
    //Etats possibles : 2e(nombre de voitures)

    public static final RushHourState GOAL_STATE =
            new RushHourState(new int[][] {
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,1,1},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0},
                    {0,0,0,0,0,0}
            } );

    public boolean isGoalState(State s){
        return s.equals(GOAL_STATE);
    }

    @Override
    public double getActionCost(State s, Action a) {
        return 0;
    }


    @Override
    public ArrayList<Action> getActions(State s) {
        return null;
    }

    @Override
    public State doAction(State s, Action a) {
        return null;
    }
}

//6*6
/*
Imaginons que c'est 6*6
int[36]






*/
