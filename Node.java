import java.util.LinkedList;

public class Node{

    private int cost;
    private Node father;
    private State state;
    private LinkedList<Node> sons;
    private Character act, fatherAct;

    public Node(int cost, State s){
        this.cost = cost;
        this.state = s;
    }

    public Node(int cost, State s, char a){
        this.cost = cost;
        this.state = s;
        this.act = a;
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

    public Character getFatherAct() {
        return fatherAct;
    }

    public boolean isGoalState(){
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

    public void setFatherAct(Character fatherAct) {
        this.fatherAct = fatherAct;
    }
}