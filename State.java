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

    public void setCube(int[][] cube) {
        this.cube = cube;
    }

    public boolean isGoal(){
        int cont = 0;
        for(int i = 0; i < cube.length; i++){
            for(int j = 0; j < cube.length; j++){
                cont++;
                if(cont != this.cube[i][j] && cont != 9){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validAction(int dx, int dy){
        int newX = position(dx, this.xZero);
        int newY = position(dy, this.xZero);

        if(newX >= 0 && newX < this.cube.length && newY >= 0 && newY < this.cube.length){
            return true;
        }
        return false;
    }

    private int position(int d, int n){
        return d + n;
    }

    public State createState(int dx, int dy){

        int[][] mat = this.cube.clone();
        int newX = position(dx, this.xZero);
        int newY = position(dy, this.xZero);
        
        mat[this.xZero][this.yZero] = mat[newX][newY];
        mat[newX][newY] = 0;
        State s = new State(mat, newX, newY);
        return s;
    }
}
