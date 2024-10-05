public class Actions {

    private char actName [] = {'D', 'R', 'U', 'L'}; // Os possíveis movimentos, considerando zero como quem realiza o movimento
    private int dx[] = {1,0,-1,0};
    private int dy[] = {0,1,0,-1};
    private int quantActions = 4;

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

}
