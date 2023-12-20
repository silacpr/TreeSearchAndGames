package ia.problemes;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.zip.DeflaterOutputStream;

public class RushHourState extends State implements HasHeuristic {

    private int[][] board;
    public int[][] vehicules;
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
            {0,0,2,0,2},
            {1,2,0,1,3},
            {2,0,3,0,3},
            {3,4,0,0,2},
            {4,5,3,1,3}
        };

        board = convertirVehiculesToBoard();
    }

    //méthode pour convertir
    //créer un board rempli de 0 par ex et remplacer les i,j par rapport aux positions des véhicules voilà

    public int[][] convertirVehiculesToBoard() {
        int[][] b = new int[][]{
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1}
        };

        Arrays.stream(vehicules).forEach(e-> System.out.println(Arrays.toString(e)));


        Arrays.stream(vehicules).forEach((vehicule) -> {
            int size = vehicule[4];
            if(vehicule[3] == 1){
                //System.out.println("problème quand c'est vertical");
               for(int i = vehicule[2]; i < vehicule[2] + size; i++){
                    b[i][vehicule[1]] = vehicule[0];

                }
            } else {
                //System.out.println("problème quand c'est horizontal");
                for(int i = vehicule[1]; i < vehicule[1] + size; i++){
                    b[vehicule[2]][i] = vehicule[0];

                }
            }
        });

        return b;
    }


    public RushHourState(int[][] vehicules) {
        this.vehicules = vehicules.clone();
        board = convertirVehiculesToBoard();
    }

    /*
    Déplacer un véhicule vers l'avant UP, RIGHT
     */
    public void moveForward(int num){
        System.out.println("avant MoveForward");
        Arrays.stream(vehicules).forEach(e-> System.out.println(Arrays.toString(e)));
        for (int i = 0 ; i < this.vehicules.length ; i++) {
            if (this.vehicules[i][0]==num){
                if (this.vehicules[i][3]==1){
                    //System.out.println("valeur position du véhicule avant : "+this.vehicules[i][2]);
                    this.vehicules[i][2]++;
                    //System.out.println("valeur position du véhicule après : "+this.vehicules[i][2]);
                }else{
                    this.vehicules[i][1]++;
                }
                break;
            }
        }


    }

    /*
    Déplacer un véhicule vers l'arrière DOWN, LEFT
     */
    public void moveBackward(int num){
        for (int i = 0 ; i < this.vehicules.length ; i++) {
            if (this.vehicules[i][0]==num){
                if (this.vehicules[i][3]==1){
                    this.vehicules[i][2]--;
                }else{
                    this.vehicules[i][1]--;
                }
                break;
            }
        }

    }

    @Override
    protected State cloneState() {
        return new RushHourState(this.vehicules);
    }

    @Override
    protected boolean equalsState(State o) {
        return Arrays.equals(this.board, ((RushHourState) o).getBoard());
    }

    public int[][] getBoard(){
        return board;
    }


    @Override
    protected int hashState() {
        return Arrays.hashCode(vehicules);
    }

    @Override
    public double getHeuristic() {
        return 0;
    }

    public static void main(String[] args) {
        RushHourState test = new RushHourState();
        Arrays.stream(test.convertirVehiculesToBoard()).forEach(e-> System.out.println(Arrays.toString(e)));
    }



    public boolean isLegal(Action a){
        if (RushHour.VEHICULE1_UP.equals(a)) {
            return DeplacementLegal("UP", 1);
        } else if (RushHour.VEHICULE1_DOWN.equals(a)) {
            return DeplacementLegal("DOWN", 1);
        } else if (RushHour.VEHICULE2_RIGHT.equals(a)) {
            return DeplacementLegal("RIGHT", 2);
        } else if (RushHour.VEHICULE2_LEFT.equals(a)) {
            return DeplacementLegal("LEFT", 2);
        } else if (RushHour.VEHICULE3_RIGHT.equals(a)) {
            return DeplacementLegal("RIGHT", 3);
        } else if (RushHour.VEHICULE3_LEFT.equals(a)) {
            return DeplacementLegal("LEFT", 3);
        } else if (RushHour.VEHICULE4_UP.equals(a)) {
            return DeplacementLegal("UP", 4);
        } else if (RushHour.VEHICULE4_DOWN.equals(a)) {
            return DeplacementLegal("DOWN", 4);
        } else if (RushHour.VEHICULE_ROUGE_RIGHT.equals(a)) {
            return DeplacementLegal("RIGHT", 0);
        } else if (RushHour.VEHICULE_ROUGE_LEFT.equals(a)) {
            return DeplacementLegal("LEFT", 0);
        }

        return false;

    }

    public boolean DeplacementLegal(String direction, int num) {
        switch (direction) {
            case "UP" -> {
                int placement = vehicules[num][2];
                if (placement == 0) return false;
                int vehicule = board[placement - 1][vehicules[num][1]];
                if (vehicule != -1) return false;
                return true;
            }

            case "DOWN" -> {
                System.out.println("DOWN");
                Arrays.stream(vehicules).forEach(e-> System.out.println(Arrays.toString(e)));
                int placement = vehicules[num][2] + vehicules[num][4] - 1;
                System.out.println("ah le placement = " + placement);
                if (placement == 5) return false;
                int vehicule = board[placement + 1][vehicules[num][1]];
                if (vehicule != -1) return false;
                System.out.println("c'est okey");
                return true;
            }

            case "LEFT" -> {

                int placement = vehicules[num][1];
                if (placement == 0) return false;
                int vehicule = board[vehicules[num][2]][placement - 1];
                if (vehicule != -1) return false;
                return true;
            }

            case "RIGHT" -> {

                int placement = vehicules[num][1] + vehicules[num][4] - 1;
                if (placement == 5) return false;
                int vehicule = board[vehicules[num][2]][placement + 1];
                if (vehicule != -1) return false;
                return true;

            }
        }
        return false;
    }



    public void imprimer(){
        Arrays.stream(board).forEach(e-> System.out.println(Arrays.toString(e)));
    }


    public String toString(){
        return Arrays.toString(board);
    }
}

