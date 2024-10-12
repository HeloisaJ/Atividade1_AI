import java.util.LinkedList;
import java.util.Stack;

public class Node{

    private int cost;
    private Node father; // Coloquei por conta do conceito de como um nó é formado segundo o livro, mas não foi necessário
    private State state;
    private LinkedList<Node> sons;
    private Character act;
    private Stack<Character> acts; // Stack de ações que foram realizadas em um nó até o momento, utilizado no algoritmo 2, para garantir a recuperação do estado anterior sem uma estrutura de dados

    public Node(int cost, State s){
        this.cost = cost;
        this.state = s;
    }

    public Node(int cost, State s, Node f, char a){
        this.cost = cost;
        this.state = s;
        this.father = f;
        this.act = a;
    }

    public int getCost() {
        return cost;
    }

    public LinkedList<Node> getSons() {
        return sons;
    }

    public State getState() {
        return state;
    }

    public Character getAct() {
        return act;
    }

    public int getActsSize(){
        return this.acts.size();
    }

    public Character peekLastAction(){ // Obter o movimento no topo da stack
        return this.acts.peek();
    }

    public void popLastAction(){ // Retirar o último movimento do topo da stack
        this.acts.pop();
    }

    public void addAction(Character c){ // Adicionar um movimento na stack
        this.acts.add(c);
    }

    public void initializeActs(){ // Inicializar a stack de ações só quando for necessário
        this.acts = new Stack<>();
    }

    public boolean isGoalState(){ // Retornar se o nó é nó objetivo ou não
        return this.state.isGoal();
    }

    public void setSons(LinkedList<Node> n){
        this.sons = n;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setAct(Character act) {
        this.act = act;
    }
}