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
    public int[] convV;


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
            {2,0,3,0,3},//modif taille pour que ce soit une v
            {3,4,0,0,2},
            {4,5,3,1,3}
        };

        board = convertirVehiculesToBoard();
        convV = convertTo1DArray(vehicules);
    }

    public RushHourState(int[][] vehicules) {
        this.vehicules = vehicules.clone();
        board = convertirVehiculesToBoard();
        convV = convertTo1DArray(vehicules);
    }

    //méthode pour convertir
    //créer un board rempli de 0 par ex et remplacer les i,j par rapport aux positions des véhicules voilà

    public int[][] convertirVehiculesToBoard() {
        int[][] b = new int[][]{
//                {-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1},
//                {-1,-1,-1,-1,-1,-1}
                {-1,-2,-3,-4,-5,-6},
                {-7,-8,-9,-10,-11,-12},
                {-13,-14,-15,-16,-17,-18},
                {-19,-20,-21,-22,-23,-24},
                {-25,-26,-27,-28,-29,-30},
                {-31,-32,-33,-34,-35,-36}
        };

        //Arrays.stream(vehicules).forEach(e-> System.out.println(Arrays.toString(e)));


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

    /*
    Déplacer un véhicule vers l'avant UP, RIGHT
     */
    public void moveForward(int num){
        //System.out.println("avant MoveForward");
        //Arrays.stream(vehicules).forEach(e-> System.out.println(Arrays.toString(e)));
        for (int i = 0 ; i < this.vehicules.length ; i++) {
            if (this.vehicules[i][0]==num){
                if (this.vehicules[i][3]==1){
                    //System.out.println("valeur position du véhicule avant : "+this.vehicules[i][2]);
                    this.vehicules[i][2]--;
                    //System.out.println("valeur position du véhicule après : "+this.vehicules[i][2]);
                }else{
                    this.vehicules[i][1]++;
                }
                break;
            }
        }
        this.board = convertirVehiculesToBoard();
        this.convV = convertTo1DArray(vehicules);
    }

    /*
    Déplacer un véhicule vers l'arrière DOWN, LEFT
     */
    public void moveBackward(int num){
        for (int i = 0 ; i < this.vehicules.length ; i++) {
            if (this.vehicules[i][0]==num){
                if (this.vehicules[i][3]==1){
                    this.vehicules[i][2]++;
                }else{
                    this.vehicules[i][1]--;
                }
                break;
            }
        }
        this.board = convertirVehiculesToBoard();
        this.convV = convertTo1DArray(vehicules);

    }

    @Override
    protected State cloneState() {
        return new RushHourState(this.vehicules);
    }

    @Override
    protected boolean equalsState(State o) {
        //int[] e = ((RushHourState) o).convertTo1DArray(((RushHourState) o).getVehicules());
        return Arrays.equals(convV,((RushHourState) o).getConvV());
    }

    public int[] convertTo1DArray(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] result = new int[rows * cols];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[index++] = matrix[i][j];
            }
        }

        return result;
    }

    public int[] getConvV(){
        return convV;
    }

    public int[][] getBoard(){
        return board;
    }

    public int[][] getVehicules(){return vehicules;}

    @Override
    protected int hashState() {
        return Arrays.hashCode(convV);
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
                //System.out.println("UP"+ num);
                //System.out.println("le numéro du véhicule = " + num);
                int placement = vehicules[num][2];
                if (placement == 0) return false;
                int vehicule = board[placement - 1][vehicules[num][1]];

                //System.out.println("véhicule ou pas ? " +vehicule);
                if (vehicule > -1) return false;
                return true;
            }

            case "DOWN" -> {
                System.out.println("DOWN");
                System.out.println("le numéro du véhicule = "+ num);
                int placement = vehicules[num][2] + vehicules[num][4] - 1;
                System.out.println("placement = " +placement);
                imprimer();
                if (placement == 5) return false;

                int vehicule = board[placement + 1][vehicules[num][1]];
                System.out.println("véhicule ou pas ? " +vehicule);
                if (vehicule > -1) return false;
                return true;
            }

            case "LEFT" -> {
                //System.out.println("LEFT");
                //System.out.println("le numéro du véhicule = " + num);
                int placement = vehicules[num][1];
                if (placement == 0) return false;
                int vehicule = board[vehicules[num][2]][placement - 1];
                //System.out.println("véhicule ou pas ? " +vehicule);
                if (vehicule > -1) return false;
                return true;
            }

            case "RIGHT" -> {

                //System.out.println("RIGHT");
                //System.out.println("le numéro du véhicule = " + num);
                int placement = vehicules[num][1] + vehicules[num][4] - 1;
                if (placement == 5) return false;
                int vehicule = board[vehicules[num][2]][placement + 1];
                //System.out.println("véhicule ou pas ? " +vehicule);
                if (vehicule > -1) return false;
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

