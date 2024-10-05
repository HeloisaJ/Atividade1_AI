public class State {
    
    private int cube[][];

    public State(int c[][]){
        this.cube = c;
    }

    public int[][] getCube() {
        return cube;
    }

    public void setCube(int[][] cube) {
        this.cube = cube;
    }

    public boolean isGoal(){
        int cont = 0;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                cont++;
                if(cont != this.cube[i][j] && cont != 9){
                    return false;
                }
            }
        }
        return true;
    }
}
