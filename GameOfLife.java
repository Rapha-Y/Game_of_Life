public class GameOfLife {
    private static final int MAX_SIZE = 50; //2048;
    private static final int GEN_NUM = 5; //2000;
    private static final int THREAD_NUM = 1;
    private static final int LIVING = 1;
    private static final int DEAD = 0;

    private int grid[][];
    private int newgrid[][];

    private void fill_grids() {
        this.grid = new int[this.MAX_SIZE][this.MAX_SIZE];
        this.newgrid = new int[this.MAX_SIZE][this.MAX_SIZE];

        for (int i = 0; i < this.MAX_SIZE; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                this.grid[i][j] = this.DEAD;
                this.newgrid[i][j] = this.DEAD;
            }
        }

        //Glider
        int row = 1;
        int col = 1;
        this.grid[row][col + 1] = this.LIVING;
        this.grid[row + 1][col + 2] = this.LIVING;
        this.grid[row + 2][col] = this.LIVING;
        this.grid[row + 2][col + 1] = this.LIVING;
        this.grid[row + 2][col + 2] = this.LIVING;

        //R-pentonimo
        row = 10;
        col = 30;
        grid[row][col + 1] = this.LIVING;
        grid[row][col + 2] = this.LIVING;
        grid[row + 1][col] = this.LIVING;
        grid[row + 1][col + 1] = this.LIVING;
        grid[row + 2][col + 1] = this.LIVING;
    }

    private int getNeighbors(int i, int j) {
        int prev_i = i - 1;
        int prev_j = j - 1;
        int next_i = i + 1;
        int next_j = j + 1;

        //loop through the board's edges
        if (prev_i < 0) {
            prev_i = this.MAX_SIZE - 1;
        }
        if (prev_j < 0) {
            prev_j = this.MAX_SIZE - 1;
        }
        if (next_i > this.MAX_SIZE - 1) {
            next_i = 0;
        }
        if (next_j > this.MAX_SIZE - 1) {
            next_j = 0;
        }

        //count number of living neighbors
        int neighbor_num = 0;
        if (grid[prev_i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[prev_i][j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[prev_i][next_j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[i][next_j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[next_i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[next_i][j] == this.LIVING) {
            neighbor_num++;
        }
        if (grid[next_i][next_j] == this.LIVING) {
            neighbor_num++;
        }

        return neighbor_num;
    }

    private void copy_newgrid() {
        for (int i = 0; i < this.MAX_SIZE; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                this.grid[i][j] = this.newgrid[i][j];
            }
        }
    }

    //for visualization only
    private void show_grid() {
        for (int i = 0; i < this.MAX_SIZE; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                if (this.grid[i][j] == this.DEAD) {
                    //System.out.print('D');
                    System.out.print(this.getNeighbors(i, j));
                } else {
                    //System.out.print('L');
                    System.out.print(this.getNeighbors(i, j));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.fill_grids();
        game.show_grid();
    }
}