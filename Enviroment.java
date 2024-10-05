import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Enviroment {
    
    private Node root; // estrutura de dados, árvore
    private Actions actions;

    public Enviroment(Actions a){
        this.actions = a;
    }

    public Node executeAlgorithm(State s){
        this.root = new Node(0, s);
        return iterativeDeepening();
    }

    private Node iterativeDeepening(){
        Node n;
        for(int i = 1; i < Integer.MAX_VALUE; i++){
            n = depthLimitedSearch(this.root, i);
            if(n.isGoalState()){
                return n;
            }
            else if(n.getCost() <= i){
                // sem solução
            }
        }
        
    }

    private Node depthLimitedSearch(Node r, int l){
        Set<Node> reached = new HashSet<>();
        Stack<Node> frontier = new Stack<>();
        frontier.add(r);

        while(!frontier.isEmpty()){
            r = frontier.pop();
            if(r.isGoalState() || r.getCost() > l){
                return r;
            }
            else if(!reached.contains(r)){
                LinkedList<Node> child = new LinkedList<>();
                // expand
            }
        }
        return r;
    }

    private void expand(LinkedList<Node> child){
        for(int i = 0; i < this.actions.getQuantActions(); i++){
            if(){

            }
        }
    }
}
