public class Main {
    public static void main(String[] args) {
        //int mat[][] = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};
        int mat[][] = {{1, 2, 3}, {4, 5, 6}, {0, 8, 7}}; // Não funciona
        State s = new State(mat);
        
        Actions a = new Actions();
        Enviroment env = new Enviroment(a);
        Node res = env.executeAlgorithm(s);

        res.getState().showState();
        System.out.println(res.getCost());
    }
}
