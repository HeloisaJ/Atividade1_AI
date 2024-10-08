public class Main {
    public static void main(String[] args) {
        int mat[][] = {{1, 2, 3}, {4, 5, 6}, {0, 7, 8}}; // Funcionou, correto
        //int mat[][] = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}}; // Sem solução, funcionou
        State s = new State(mat);
        
        Actions a = new Actions();
        EnvWithData env = new EnvWithData(a);
        CompareFunctions c = new CompareFunctions(env, env);

        c.executeComparison(s);
    }
}
