import java.util.Arrays;

public class State {
    
    private int board[][]; // Tabuleiro do jogo
    private int xZero, yZero; // Posição (x, y) do espaço vazio (0)

    public State(int c[][]){
        this.board = c;
        defineXY();
    }

    public State(int c[][], int x, int y){
        this.board = c;
        this.xZero = x;
        this.yZero = y;
    }

    private void defineXY(){ // Segundo o board atual, determina a posição (x, y) do espaço vazio (0)
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] == 0){
                    this.xZero = i;
                    this.yZero = j;
                    break;
                }
            }
        }
    }

    public boolean isGoal(){ // Verifica se o estado atual é o estado objetivo ({{0, 1, 2}, {3, 4, 5}, {6, 7, 8}})
        int cont = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if(cont != this.board[i][j]){
                    return false;
                }
                cont++;
            }
        }
        return true;
    }

    public boolean validAction(int dx, int dy){ // Valida se é possível realizar um movimento de acordo com dx e dy

        int newX = position(dx, this.xZero); 
        int newY = position(dy, this.yZero); 

        if(newX >= 0 && newX < this.board.length && newY >= 0 && newY < this.board.length){ // Verifica se o movimento está de acordo com os limites do board
            return true;
        }
        return false;
    }

    private int position(int d, int n){ // Cálculo da nova posição 
        return d + n;
    }

    public State createState(int dx, int dy){ // Cria um novo estado com base em um novo movimento

        int[][] mat = new int[this.board.length][this.board.length]; // Cria uma nova matriz
        copy(mat);

        int newX = position(dx, this.xZero);
        int newY = position(dy, this.yZero);
        
        move(newX, newY, mat); // Realiza as modificações na nova matriz
        State s = new State(mat, newX, newY); // Cria um novo estado com a nova matriz
        return s;
    }

    public State copyState(){ // Realizar a cópia de um estado
        int[][] mat = new int[this.board.length][this.board.length]; // Cria uma nova matriz
        copy(mat);

        State s = new State(mat, this.xZero, this.yZero); // Cria um novo estado com a nova matriz
        return s;
    }

    private void move(int newX, int newY, int mat[][]){ // Realizar a movimentação do espaço em branco (troca) em uma matriz mat
        mat[this.xZero][this.yZero] = mat[newX][newY];
        mat[newX][newY] = 0;
    }

    public void modifyState(int dx, int dy){ // Semelhante ao createState, mas só modifica o board do estado atual

        int newX = position(dx, this.xZero);
        int newY = position(dy, this.yZero);

        move(newX, newY, this.board);
        this.xZero = newX;
        this.yZero = newY;

    }

    private void copy(int mat[][]){ // Copia os dados do board para uma matriz mat
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                mat[i][j] = this.board[i][j];
            }
        }
    }

    public void showState(){ // Mostra os dados do board no terminal
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public boolean equals(Object o){ // Função de equals para que o Set do Java consiga diferenciar dois estados diferentes.
        if(this == o){
            return true;
        }
        State s = (State) o;
        return Arrays.deepEquals(this.board, s.board);
    }

    @Override
    public int hashCode(){ // Função de hash code para que o Set do Java consiga localizar os estados.
        return Arrays.deepHashCode(this.board);
    }
}
