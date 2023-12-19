package ia.problemes;

import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;

import java.util.Arrays;

public class RushHourState extends State implements HasHeuristic {


    private int[][] board = null;
    private int[][] vehicules;
    /*
    numéro du véhicule
    pos x
    pos y
    direction (0 si horizontal 1 si vertical)
    taille

    tête = haut / gauche
     */

    public RushHourState() {
        vehicules = new int[][] {
            {1,2,0,1,3},
            {-1,0, 2, 0, 2},
            {2,0,3,0,3},
            {3,4,0,0,2},
            {4,5,3,1,3}
        };

        board = convertirVehiculesToBoard();
    }

    //méthode pour convertir
    //créer un board rempli de 0 par ex et remplacer les i,j par rapport aux positions des véhicules voilà

    public int[][] convertirVehiculesToBoard(){
        int[][] b = new int[][]{
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0},
            {0,0,0,0,0,0}
        };
        Arrays.stream(vehicules).forEach((vehicule) -> {
            
        });

        return new int[][] {};
    }


    public RushHourState(int[][] vehicules) {
        vehicules = vehicules.clone();
    }

    @Override
    protected State cloneState() {
        return null;
    }

    @Override
    protected boolean equalsState(State o) {
        return false;
    }

    @Override
    protected int hashState() {
        return 0;
    }

    @Override
    public double getHeuristic() {
        return 0;
    }
}
