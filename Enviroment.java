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
        Node n = null;
        for(int i = 1; i <= 20; i++){
            n = depthLimitedSearch(this.root, i);
            if(n.isGoalState()){
                break;
            }
            else if(n.getCost() + 1 < i){
                System.out.println("Não existe uma solução");
                break;
            }
        }
        return n;
    }

    private Node depthLimitedSearch(Node r, int l){
        Set<Node> reached = new HashSet<>();
        Stack<Node> frontier = new Stack<>();
        frontier.add(r);

        while(!frontier.isEmpty()){
            r = frontier.pop();
            if(r.isGoalState()){
                return r;
            }
            else if(r.getCost() + 1 < l && !reached.contains(r)){
                LinkedList<Node> child = new LinkedList<>();
                if(r.getSons() != null){
                    child = r.getSons();
                }
                else{
                    expand(r, child);
                    r.setSons(child);
                }

                addChildToFrontier(child, frontier);
            }
            reached.add(r);
        }

        return r;
    }

    private void addChildToFrontier(LinkedList<Node> child, Stack<Node> frontier){
        for(int i = 0; i < child.size(); i++){
            frontier.add(child.get(i));
        }
    }

    private void expand(Node r, LinkedList<Node> child){
        int dx[] = this.actions.getDx();
        int dy[] = this.actions.getDy();
        char a[] = this.actions.getActName();
        Node c;
        for(int i = 0; i < this.actions.getQuantActions(); i++){
            if(r.getState().validAction(dx[i], dy[i])){
                c = new Node(r.getCost() + 1, r.getState().createState(dx[i], dy[i]), r, a[i]);
                child.add(c);
            }
        }
    }
}
