import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EnvModState implements Environment{
    
    private Actions actions; // Conjunto de ações disponíveis 

    public EnvModState(Actions a){
        this.actions = a;
    }

    @Override
    public Node executeAlgorithm(State s) {
        Node initial = new Node(0, s); // Cria um nó inicial que apresenta custo 0, pois nenhum movimento foi realizado
        initial.initializeActs();
        return iterativeDeepening(initial);
    }

    private Node iterativeDeepening(Node r){
        Node n = null;
        int i;
        boolean goal = false;
        for(i = 1; i <= 20; i++){ // Loop com a profundidade

            n = depthLimitedSearch(r, i); // Chama a busca com limite de profundidade
            
            if(n.isGoalState()){ // Verifica se é o estado goal
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
        Set<State> reached = new HashSet<>(); // Cria um set de estados alcançados
        Stack<Node> frontier = new Stack<>(); // Cria uma estrutura de dados stack para os nós (f(n)), nesse caso só vai ter um nó por vez
        frontier.add(r);
        State s;

        int dx[] = this.actions.getDx();
        int dy[] = this.actions.getDy();
        char a [] = this.actions.getActName();

        while(!frontier.isEmpty()){
            r = frontier.pop(); // Retira um nó da fronteira
            s = r.getState();

            if(s.isGoal()){ // Verifica se apresenta um estado objetivo
                return r;
            }
            else if(r.getCost() + 1 < l && !reached.contains(s)){ // Caso ele não tenha chegado no limite de profundidade e não foi alcançado ainda
                // Expandir 

                int pos = 0, quant = r.getActsSize();

                if(quant > r.getCost()){ // Se a quantidade de elementos da stack for maior que o custo do nó atual, preciso modificar a última ação realizada
                    char act2, act = r.peekLastAction(); // vê a última ação realizada
                    r.popLastAction();
                    if(quant - 1 > 0){ // verifica se existe outra ação antes dela
                        act2 = r.peekLastAction(); 
                        pos = findNextMove(r, s, this.actions.getActsPosition(act) + 1, this.actions.getOpositeAction(act2), dx, dy);
                    }
                    else{
                        pos = findNextMove(r, s, this.actions.getActsPosition(act) + 1, null, dx, dy);
                    }
                }
                else if(quant == r.getCost()){ // Se a quantidade de elementos da stack for igual ao custo do nó atual, preciso realizar uma nova ação
                    
                    if(quant > 0){ // verifica se existe outra ação antes dela
                        Character act = r.peekLastAction();
                        pos = findNextMove(r, s, 0, this.actions.getOpositeAction(act), dx, dy);
                    }
                    else{
                        pos = findNextMove(r, s, 0, null, dx, dy);
                    }
                }

                if(pos == this.actions.getQuantActions()){ // Verifica se não existe mais ações para aquele nó
                    reached.add(s.copyState()); 
                    frontier.add(r); // Fazer com que ele repita o loop para acessar a parte de desfazer uma ação em um estado
                }
                else{ // Existe uma ação a ser realizada

                    s.modifyState(dx[pos], dy[pos]);
                    r.addAction(a[pos]); // Adicionar ação a pilha
                    r.setCost(r.getCost() + 1); // Muda o custo do nó
                    frontier.add(r); // Adiciona o novo nó a fronteira
                }
            }
            else{
                int quant = r.getActsSize();
                reached.add(s.copyState());

                if(quant > 0){ // Verifica se a pilha de ações está vazia

                    // Desfaz o efeito de uma ação em um estado
                    int pos = this.actions.getOpositeAction(r.peekLastAction()); // Verifica qual a ação oposta a última ação realizada
                    s.modifyState(dx[pos], dy[pos]);
                    r.setCost(r.getCost() - 1); // Muda o custo do nó
                    frontier.add(r); // Adiciona o nó anterior a fronteira
                }
            }
        }

        return r;
    }    


    private int findNextMove(Node r, State s, int ini, Integer oppositeAct, int [] dx, int [] dy){ // Verifica quais são as ações possíveis no momento
        int tam = this.actions.getQuantActions();
        for(Integer i = ini; i < tam; i++){ // // De acordo com a quantidade de ações possíveis

            if(s.validAction(dx[i], dy[i]) && i != oppositeAct){ // Se for válida e não for a ação imediatamente oposta, retorna a posição ação para dx e dy
                return i;
            }
        }
        return tam;
    }

    /*
     * Em relação ao findNextMove e a presença de um Integer opositeAct, isso se deve a
     * presença de ações que revertem outras ações, para impedir que ele considere como reached
     * um estado mais próximo ao estado inicial.
     */
}
