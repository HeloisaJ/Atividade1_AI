import java.util.Map;

public class Actions {

    private char actName [] = {'D', 'R', 'U', 'L'}; 
    private int dx[] = {1,0,-1,0};
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
        int pos = acts.get(c); 
        if(pos <= 1){
            return pos + 2;
        }
        return pos - 2;
    }

    public int getActsPosition(char c){
        return acts.get(c);
    }
}
