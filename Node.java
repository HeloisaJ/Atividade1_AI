import java.util.LinkedList;

public class Node{

    private int cost;
    private Node father;
    private State state;
    private LinkedList<Node> sons;
    private Character act;

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

    public boolean isGoalState(){
        return this.state.isGoal();
    }
}