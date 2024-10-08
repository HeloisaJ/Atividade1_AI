public class CompareFunctions {
    
    private Environment env1;
    private Environment env2;

    public CompareFunctions(Environment e1, Environment e2){
        this.env1 = e1;
        this.env2 = e2;
    }

    public void executeComparison(State s){
        long res = executeEnv(s, env1);
        System.out.println("Tempo para execução do algoritmo 1: " + res/1000000.0 + " ms.");

        System.out.println("-----------------------------------------------------------------------");

        long res2 = executeEnv(s, env2);
        System.out.println("Tempo para execução do algoritmo 2: " + res/1000000.0 + " ms.");

        System.out.println("-----------------------------------------------------------------------");

        int x = 1;
        if(res2 < res){
            x = 2;
        }
        System.out.println("O algoritmo que executou mais rápido foi o " + x);
        System.out.println("");
    }

    private long executeEnv(State s, Environment e){
        long startTime = System.nanoTime();
        Node n = e.executeAlgorithm(s);
        long endTime = System.nanoTime() - startTime;
        revealLastState(n);
        return endTime;
    }

    private void revealLastState(Node n){
        System.out.println("Estado final: ");
        n.getState().showState();
        System.out.println("Quantidade de movimentos (profundidade): " + n.getCost());
    }
}
