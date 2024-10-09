import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EnvModState implements Environment{
    
    private Actions actions;

    public EnvModState(Actions a){
        this.actions = a;
    }

    @Override
    public Node executeAlgorithm(State s) {
        Node initial = new Node(0, s);
        initial.setAct('-');
        return iterativeDeepening(initial);
    }

    private Node iterativeDeepening(Node r){
        Node n = null;
        int i;
        for(i = 1; i <= 20; i++){
            n = depthLimitedSearch(r, i);
            if(n.isGoalState()){
                System.out.println("Solução encontrada !");
                break;
            }
            else if(n.getCost() + 1 != i){
                System.out.println("Não existe uma solução para esse estado.");
                break;
            }
        }
        System.out.println("Iteração final: " + i);
        System.out.println();
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
                expand(r, s, frontier);
            }
            else{
                
            }
        }

        return r;
    }    

    private void expand(Node r, State s, Stack<Node> frontier){
        int dx[] = this.actions.getDx();
        int dy[] = this.actions.getDy();
        char a[] = this.actions.getActName();
        char c = r.getAct();
        int pos;
        Node n;
        
        for(int i = 0; i < this.actions.getQuantActions(); i++){
            if(s.validAction(dx[i], dy[i])){

                makeMove(r, s, dx[i], dy[i], a[i]);
                n = new Node(r.getCost() + 1, s, a[i]);
                frontier.add(n);
                pos = actions.getOpositeAction(i);
                makeMove(r, s, dx[pos], dy[pos], c);
            }
        }
    }

    private void makeMove(Node r, State s, int dx, int dy, char ac){
        s.modifyState(dx, dy);
        r.setAct(ac);
    }
}
