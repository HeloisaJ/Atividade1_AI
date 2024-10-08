public class EnvModState implements Environment{
    
    private Actions actions;

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

    }    


    private void expand(Node r, State s, LinkedList<Node> child){

    }
}
