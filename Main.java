public class Main {
    public static void main(String[] args) {
        //int mat[][] = {{0, 8, 7}, {6, 5, 4}, {3, 2, 1}}; // Com solução
        int mat[][] = {{1, 2, 5}, {3, 4, 0}, {6, 7, 8}}; // Com solução
        //int mat[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}; // Teste
        //int mat[][] = {{1, 4, 2}, {7, 5, 8}, {3, 0, 6}}; // Sem solução
        
        State s = new State(mat); // Criação do estado inicial com base na matriz
        
        Actions a = new Actions();
        EnvWithData env = new EnvWithData(a); // Ambiente com estrutura de dados
        EnvModState env2 = new EnvModState(a); // Ambiente sem estrutura de dados

        CompareFunctions c = new CompareFunctions(env, env2);

        c.executeComparison(s); // Realizar comparação entre as duas funções
    }
}
