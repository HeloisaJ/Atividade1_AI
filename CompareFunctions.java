public class CompareFunctions {
    
    private Environment env1;
    private Environment env2;

    public CompareFunctions(Environment e1, Environment e2){
        this.env1 = e1;
        this.env2 = e2;
    }

    public void executeComparison(State s){
        long res, endTime, startTime = System.nanoTime();
        executeEnv(s, env1);
        endTime = System.nanoTime();
        res = (endTime - startTime);
        System.out.println("Tempo para execução do algoritmo 1: " + res/1000000.0 + " ms.");

        System.out.println("-----------------------------------------------------------------------");

        long res2, endTime2, startTime2 = System.nanoTime();
        executeEnv(s, env2);
        endTime2 = System.nanoTime();
        res2 = (endTime2 - startTime2);
        System.out.println("Tempo para execução do algoritmo 2: " + res2/1000000.0 + " ms.");

        System.out.println("-----------------------------------------------------------------------");

        int x = 1;
        if(res2 < res){
            x = 2;
        }
        System.out.println("O algoritmo que executou mais rápido foi o " + x);
        System.out.println("");
    }

    private void executeEnv(State s, Environment e){
        Node n = e.executeAlgorithm(s);
        revealLastState(n);
    }

    private void revealLastState(Node n){
        System.out.println("Estado final: ");
        n.getState().showState();
        System.out.println("Quantidade de movimentos (profundidade): " + n.getCost());
    }
}
