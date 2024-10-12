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
        initial.initializeActs();
        return iterativeDeepening(initial);
    }

    private Node iterativeDeepening(Node r){
        Node n = null;
        int i;
        boolean goal = false;
        for(i = 1; i <= 20; i++){
            n = depthLimitedSearch(r, i);
            if(n.isGoalState()){
                System.out.println("Solução encontrada !");
                goal = true;
                break;
            }
        }
        if(!goal){
            System.out.println("Não existe uma solução para esse estado ou ele chegou na iteração final.");
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

                int pos = 0, quant = r.getActsSize();

                if(quant > r.getCost()){
                    char act2, act = r.peekLastAction();
                    r.popLastAction();
                    if(quant - 1 > 0){
                        act2 = r.peekLastAction();
                        pos = findNextMove(r, s, this.actions.getActsPosition(act) + 1, this.actions.getOpositeAction(act2), dx, dy);
                    }
                    else{
                        pos = findNextMove(r, s, this.actions.getActsPosition(act) + 1, null, dx, dy);
                    }
                }
                else if(quant == r.getCost()){
                    
                    if(quant > 0){
                        Character act = r.peekLastAction();
                        pos = findNextMove(r, s, 0, this.actions.getOpositeAction(act), dx, dy);
                    }
                    else{
                        pos = findNextMove(r, s, 0, null, dx, dy);
                    }
                }

                if(pos == this.actions.getQuantActions()){
                    reached.add(s.copyState());
                    frontier.add(r); // Fazer ele voltar pra voltar um estado
                }
                else{
                    s.modifyState(dx[pos], dy[pos]);
                    r.addAction(a[pos]); // Adicionar ação a pilha
                    r.setCost(r.getCost() + 1);
                    frontier.add(r);
                }
            }
            else{
                int quant = r.getActsSize();
                reached.add(s.copyState());

                if(quant > 0){ // Voltar um estado

                    int pos = this.actions.getOpositeAction(r.peekLastAction()); 
                    s.modifyState(dx[pos], dy[pos]);
                    r.setCost(r.getCost() - 1);
                    frontier.add(r);
                }
            }
        }

        return r;
    }    


    private int findNextMove(Node r, State s, int ini, Integer opositeAct, int [] dx, int [] dy){
        int tam = this.actions.getQuantActions();
        for(Integer i = ini; i < tam; i++){
            if(s.validAction(dx[i], dy[i]) && i != opositeAct){
                return i;
            }
        }
        return tam;
    }
}
