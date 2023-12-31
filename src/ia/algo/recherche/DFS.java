package ia.algo.recherche;

import java.util.ArrayList;
import java.util.HashSet;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.TreeSearch;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.SearchNode;


public class DFS extends TreeSearch {
    public DFS(SearchProblem prob, State initial_state) {
        super(prob, initial_state);
    }
    @Override
    public boolean solve(){
//      Créer un noeud correspondant à l'état initial
        SearchNode node = SearchNode.makeRootSearchNode(intial_state);
//      Initialiser la frontière avec ce noeud          // une collection de noeud pour stocker les noeuds à étendre
        frontier.add(node);
//      Initialiser l'ensemble des états visités à vide // une collection d'états (sans répétition)
        explored = new HashSet<>();
//      Tant que la frontière n'est pas vide
        while(!frontier.isEmpty()) {
            //System.out.println("Frontier size = "+frontier.size());
//          Retirer un noeud de la frontière selon une stratégie // la fameuse stratégie
            SearchNode actuel = frontier.get(frontier.size()-1);
            frontier.remove(frontier.size()-1);
//          Si le noeud contient un état but
            if (problem.isGoalState(actuel.getState())) {
                end_node = actuel;
                return true;
            }
//          Sinon
            else {
//              Ajouter son état à l'ensemble des états visités
                explored.add(actuel.getState());
//              Étendre les enfants du noeud
                ArrayList<Action> actions = problem.getActions(actuel.getState());
//              Pour chaque noeud enfant

                for (int i = actions.size()-1; i>=0; i--) {
                    SearchNode childnode = SearchNode.makeChildSearchNode(problem, actuel, actions.get(i));
//                  S'il n'est pas dans la frontière et si son état n'a pas été visité
                    if (!frontier.contains(childnode) && !explored.contains(childnode.getState())) {
                        //System.out.println("test");
                        frontier.add(childnode);
                    }
                }
            }
        }
//      Retourner échec
        return false;

    }
}
