package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.recherche.Problem;
import ia.framework.recherche.SearchProblem;

import java.util.ArrayList;
import java.util.Arrays;

public class RushHour extends SearchProblem {

    public static final Action VEHICULE1_UP = new Action("VEHICULE1 UP");
    public static final Action VEHICULE1_DOWN = new Action("VEHICULE1 DOWN");

    public static final Action VEHICULE2_RIGHT = new Action("VEHICULE2 RIGHT");
    public static final Action VEHICULE2_LEFT = new Action("VEHICULE2 LEFT");

    public static final Action VEHICULE3_RIGHT = new Action("VEHICULE3 RIGHT");
    public static final Action VEHICULE3_LEFT = new Action("VEHICULE3 LEFT");

    public static final Action VEHICULE4_UP = new Action("VEHICULE4 UP");
    public static final Action VEHICULE4_DOWN = new Action("VEHICULE4 DOWN");

    public static final Action VEHICULE_ROUGE_RIGHT = new Action("VEHICULE ROUGE RIGHT");
    public static final Action VEHICULE_ROUGE_LEFT = new Action("VEHICULE ROUGE LEFT");

    public RushHour(){
        // La liste des actions possibles
        ACTIONS = new Action[] {VEHICULE1_UP, VEHICULE1_DOWN, VEHICULE2_RIGHT, VEHICULE2_LEFT,
                VEHICULE3_RIGHT, VEHICULE3_LEFT, VEHICULE4_UP, VEHICULE4_DOWN, VEHICULE_ROUGE_RIGHT,
                VEHICULE_ROUGE_LEFT};
    }


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
        if (s instanceof RushHourState){
            return (((RushHourState) s).vehicules)[0][1] == 4;
        }
        return false;
    }

    @Override
    public double getActionCost(State s, Action a) {
        return 1.0;
    }


    @Override
    public ArrayList<Action> getActions(State s) {
        ArrayList<Action> actions = new ArrayList<>();
        for (Action a : ACTIONS)
            if (s instanceof RushHourState){
                if(((RushHourState)s).isLegal(a)) {
                    //System.out.println("c'est légal");
                    actions.add(a);
                }
            }
        //System.out.println("From getActions() : "+actions.size());

        return actions;
    }

    @Override
    public State doAction(State s, Action a) {
        RushHourState r = null;
        if (s instanceof RushHourState){
            r = (RushHourState) s.clone();
            //System.out.println("il garde rien");
        }
        // on copie l'état courant et on le modifie
        //System.out.println("est ce que c'est vide ici? " + r);
        if (VEHICULE1_UP.equals(a)) {
            r.moveForward(1);
        } else if (VEHICULE1_DOWN.equals(a)) {
            r.moveBackward(1);
        } else if (VEHICULE2_RIGHT.equals(a)) {
            r.moveForward(2);
        } else if (VEHICULE2_LEFT.equals(a)) {
            r.moveBackward(2);
        } else if (VEHICULE3_RIGHT.equals(a)) {
            r.moveForward(3);
        } else if (VEHICULE3_LEFT.equals(a)) {
            r.moveBackward(3);
        } else if (VEHICULE4_UP.equals(a)) {
            r.moveForward(4);
        } else if (VEHICULE4_DOWN.equals(a)) {
            r.moveBackward(4);
        } else if (VEHICULE_ROUGE_RIGHT.equals(a)) {
            r.moveForward(0);
        } else if (VEHICULE_ROUGE_LEFT.equals(a)) {
            r.moveBackward(0);
        } else {
            throw new IllegalArgumentException("Invalid " + a);
        }
        r.imprimer();
        return r;
    }
}
