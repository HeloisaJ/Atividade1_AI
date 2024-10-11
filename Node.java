import java.util.LinkedList;
import java.util.Stack;

public class Node{

    private int cost;
    private Node father;
    private State state;
    private LinkedList<Node> sons;
    private Character act;
    private Stack<Character> acts;

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

    public Character peekLastAction(){
        return this.acts.peek();
    }

    public void popLastAction(){
        this.acts.pop();
    }

    public void addAction(Character c){
        this.acts.add(c);
    }

    public void initializeActs(){
        this.acts = new Stack<>();
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
}