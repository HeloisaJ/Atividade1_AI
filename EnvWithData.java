import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class EnvWithData implements Environment{
    
    private Node root; // Estrutura de dados, árvore
    private Actions actions; // Conjunto de ações disponíveis 

    public EnvWithData(Actions a){
        this.actions = a;
    }

    public Node executeAlgorithm(State s){
        this.root = new Node(0, s); // Cria um nó inicial que apresenta custo 0, pois nenhum movimento foi realizado
        return iterativeDeepening();
    }

    private Node iterativeDeepening(){
        Node n = null;
        int i;
        for(i = 1; i <= 20; i++){ // Loop com a profundidade

            n = depthLimitedSearch(this.root, i); // Chama a busca com limite de profundidade

            if(n.isGoalState()){ // Verifica se é o estado goal
                System.out.println("Solução encontrada !");
                break;
            }
            else if(n.getCost() + 1 != i){ // É possível determinar que não existe solução quando a busca termina com um nó que não está associado ao limite de profunidade
                System.out.println("Não existe uma solução para esse estado.");
                break;
            }
        }
        System.out.println("Iteração final: " + i);
        System.out.println();
        return n;
    }

    private Node depthLimitedSearch(Node r, int l){
        Set<State> reached = new HashSet<>(); // Cria um set de estados alcançados
        Stack<Node> frontier = new Stack<>(); // Cria uma estrutura de dados stack para os nós (f(n))
        frontier.add(r); // Adiciona o nó inicial
        State s;

        while(!frontier.isEmpty()){
            r = frontier.pop(); // Retira um nó da fronteira
            s = r.getState();

            if(s.isGoal()){ // Verifica se apresenta um estado objetivo
                return r;
            }
            else if(r.getCost() + 1 < l && !reached.contains(s)){ // Caso ele não tenha chegado no limite de profundidade e não foi alcançado ainda
                LinkedList<Node> child = r.getSons();

                if(child == null){
                    child = new LinkedList<>();
                    expand(r, s, child); // Obtém os possíveis nós filhos de um nó
                    r.setSons(child);
                }

                addChildToFrontier(child, frontier); // Adiciona os nós filhos a fronteira
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
        for(int i = 0; i < this.actions.getQuantActions(); i++){ // De acordo com a quantidade de ações possíveis
            if(s.validAction(dx[i], dy[i])){ // Verifica se uma ação é válida

                c = new Node(r.getCost() + 1, s.createState(dx[i], dy[i]), r, a[i]); // Se for válida, cria um novo nó
                child.add(c);
            }
        }
    }
}
