public class Main {
    public static void main(String[] args) {
        //int mat[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}; // Teste do estado goal (OK)
        //int mat[][] = {{1, 0, 2}, {3, 4, 5}, {6, 7, 8}}; // Com solução (solução com 1 action, OK) 
        //int mat[][] = {{1, 2, 0}, {3, 4, 5}, {6, 7, 8}}; // Com solução (solução com 2 actions)
        int mat[][] = {{1, 2, 5}, {3, 4, 0}, {6, 7, 8}}; // Com solução (solução com 3 actions, AIMA, OK)

        State s = new State(mat); // Criação do estado inicial com base na matriz
        
        Actions a = new Actions();
        EnvWithData env = new EnvWithData(a); // Ambiente com estrutura de dados
        EnvModState env2 = new EnvModState(a); // Ambiente sem estrutura de dados

        CompareFunctions c = new CompareFunctions(env, env2);

        c.executeComparison(s); // Realizar comparação entre as duas funções
    }
}
