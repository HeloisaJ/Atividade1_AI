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

        int dx[] = this.actions.getDx();
        int dy[] = this.actions.getDy();
        char a [] = this.actions.getActName();

        while(!frontier.isEmpty()){
            r = frontier.pop();
            s = r.getState();

            if(s.isGoal()){
                return r;
            }
            else if(r.getCost() + 1 < l && !reached.contains(s)){
                // Expandir 
                Character c = r.getAct();

                int pos = 0;
                if(c != null){ // Ver como configurar as ações antes e depois da forma certa.
                    pos = this.actions.getActsPosition(c);
                }
                s.modifyState(dx[pos], dy[pos]);
                r.setAct(a[pos]);
                r.setFatherAct(c);
                r.setCost(r.getCost() + 1);
                frontier.add(r);
            }
            else{
                Character c = r.getAct();
                reached.add(s);

                if(c != null){ // Voltar um estado

                    int pos = this.actions.getOpositeAction(c); 
                    s.modifyState(dx[pos], dy[pos]);
                    r.setCost(r.getCost() - 1);
                    r.setAct(r.getFatherAct());
                    frontier.add(r);
                }
            }
        }

        return r;
    }    

}
