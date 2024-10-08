import java.util.Arrays;

public class State {
    
    private int cube[][];
    private int xZero, yZero;

    public State(int c[][]){
        this.cube = c;
        defineXY();
    }

    public State(int c[][], int x, int y){
        this.cube = c;
        this.xZero = x;
        this.yZero = y;
    }

    private void defineXY(){
        for(int i = 0; i < this.cube.length; i++){
            for(int j = 0; j < this.cube.length; j++){
                if(this.cube[i][j] == 0){
                    this.xZero = i;
                    this.yZero = j;
                    break;
                }
            }
        }
    }

    public int[][] getCube() {
        return cube;
    }

    public boolean isGoal(){
        int cont = 0;
        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube.length; j++){
                if(cont != this.cube[i][j]){
                    return false;
                }
                cont++;
            }
        }
        return true;
    }

    public boolean validAction(int dx, int dy){
        int newX = position(dx, this.xZero);
        int newY = position(dy, this.yZero);

        if(newX >= 0 && newX < this.cube.length && newY >= 0 && newY < this.cube.length){
            return true;
        }
        return false;
    }

    private int position(int d, int n){
        return d + n;
    }

    public State createState(int dx, int dy){

        int[][] mat = new int[this.cube.length][this.cube.length];
        copy(mat);
        int newX = position(dx, this.xZero);
        int newY = position(dy, this.yZero);
        
        mat[this.xZero][this.yZero] = mat[newX][newY];
        mat[newX][newY] = 0;
        State s = new State(mat, newX, newY);
        return s;
    }

    private void copy(int mat[][]){
        for(int i = 0; i < this.cube.length; i++){
            for(int j = 0; j < this.cube.length; j++){
                mat[i][j] = this.cube[i][j];
            }
        }
    }

    public void showState(){
        for(int i = 0; i < this.cube.length; i++){
            for(int j = 0; j < this.cube.length; j++){
                System.out.print(this.cube[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        State s = (State) o;
        return Arrays.deepEquals(this.cube, s.cube);
    }

    @Override
    public int hashCode(){
        return Arrays.deepHashCode(this.cube);
    }
}
