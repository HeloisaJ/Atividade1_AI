import java.util.Map;

public class Actions {

    private char actName [] = {'D', 'R', 'U', 'L'}; // Nome dos movimentos (Down, Right, Up, Left)
    private int dx[] = {1,0,-1,0}; // dx e dy combinados, a depender de uma posição x, indicam a forma como o movimento será realizado 
    private int dy[] = {0,1,0,-1};
    private int quantActions = 4;

    private Map<Character, Integer> acts = Map.of( 
        'D', 0, 
        'R', 1, 
        'U', 2, 
        'L', 3); // Os possíveis movimentos (nome e posição dx/dy), considerando zero como quem realiza o movimento

    public char[] getActName() {
        return actName;
    }

    public int[] getDx() {
        return dx;
    }
    
    public int[] getDy() {
        return dy;
    }

    public int getQuantActions() {
        return quantActions;
    }

    public int getOpositeAction(Character c){
        int pos = acts.get(c); // Por conta da forma como está organizada a lista de movimentos, podemos dizer que:
        if(pos <= 1){ // Se for (1)R ou (0)D, então o oposto dele vai estar a duas posições a frente (L = 3 e U = 2)
            return pos + 2;
        }
        return pos - 2;
    }

    public int getActsPosition(char c){ // Utiliza o map para obter uma conversão fácil do nome movimento com a posição dx/dy associada a ele
        return acts.get(c);
    }
}
