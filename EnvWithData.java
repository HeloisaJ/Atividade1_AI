import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class EnvWithData implements Environment{
    
    private Node root; // estrutura de dados, árvore
    private Actions actions;

    public EnvWithData(Actions a){
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
            else if(n.getCost() + 1 != i){
                System.out.println("Não existe uma solução");
                break;
            }
        }
        return n;
    }

    private Node depthLimitedSearch(Node r, int l){
        Set<State> reached = new HashSet<>();
        Stack<Node> frontier = new Stack<>();
        frontier.add(r);
        State s;

        while(!frontier.isEmpty()){
            r = frontier.pop();
            s = r.getState();

            if(s.isGoal()){
                return r;
            }
            else if(r.getCost() + 1 < l && !reached.contains(s)){
                LinkedList<Node> child = r.getSons();

                if(child == null){
                    child = new LinkedList<>();
                    expand(r, s, child);
                    r.setSons(child);
                }

                addChildToFrontier(child, frontier);
            }
            reached.add(s);
        }

        return r;
    }

    private void addChildToFrontier(LinkedList<Node> child, Stack<Node> frontier){
        for(int i = 0; i < child.size(); i++){
            frontier.add(child.get(i));
        }
    }

    private void expand(Node r, State s, LinkedList<Node> child){
        int dx[] = this.actions.getDx();
        int dy[] = this.actions.getDy();
        char a[] = this.actions.getActName();
        Node c;
        for(int i = 0; i < this.actions.getQuantActions(); i++){
            if(s.validAction(dx[i], dy[i])){

                c = new Node(r.getCost() + 1, s.createState(dx[i], dy[i]), r, a[i]);
                child.add(c);
            }
        }
    }
}
