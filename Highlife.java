import java.time.Instant;

public class Highlife implements Runnable {
    private static int MAX_SIZE = 2048;
    private static int GEN_NUM = 2000;
    private static int THREAD_NUM = 8;
    private static int LIVING = 1;
    private static int DEAD = 0;

    private static int grid[][];
    private static int newgrid[][];

    private int id;

    public Highlife(int id) {
        this.id = id;
    }

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
        if (this.grid[prev_i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[prev_i][j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[prev_i][next_j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[i][next_j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[next_i][prev_j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[next_i][j] == this.LIVING) {
            neighbor_num++;
        }
        if (this.grid[next_i][next_j] == this.LIVING) {
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

    public void run() {
        int start = (this.MAX_SIZE / this.THREAD_NUM) * this.id;
        int end = (this.MAX_SIZE / this.THREAD_NUM) * (this.id + 1);
        for (int i = start; i < end; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                int neighbor_num = this.getNeighbors(i, j);
    
                if (this.grid[i][j] == this.LIVING && (neighbor_num < 2 || neighbor_num > 3)) {
                    this.newgrid[i][j] = this.DEAD;
                } else if (this.grid[i][j] == this.DEAD && (neighbor_num == 3 || neighbor_num == 6)) {
                    this.newgrid[i][j] = this.LIVING;
                } else {
                    this.newgrid[i][j] = this.grid[i][j];
                }
            }
        }
    }

    private int count_living() {
        int living_num = 0;
        for (int i = 0; i < this.MAX_SIZE; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                if (this.grid[i][j] == this.LIVING) {
                    living_num++;
                }
            }
        }
        return living_num;
    }

    //for visualization only
    private void show_grid() {
        for (int i = 0; i < this.MAX_SIZE; i++) {
            for (int j = 0; j < this.MAX_SIZE; j++) {
                if (this.grid[i][j] == this.DEAD) {
                    System.out.print('D');
                } else {
                    System.out.print('L');
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private int getGenNum() {
        return this.GEN_NUM;
    }

    private int getThreadNum() {
        return this.THREAD_NUM;
    }

    public static void main(String[] args) {
        long start = Instant.now().toEpochMilli();

        Highlife game = new Highlife(8);
        game.fill_grids();
        System.out.println("Initial state: " + game.count_living());

        for (int i = 0; i < game.getGenNum(); i++) {
            for (int j = 0; j < game.getThreadNum(); j++) {
                Highlife gofRunnable = new Highlife(j);
                Thread gofThread = new Thread(gofRunnable);
                gofThread.start();

                try {
                    gofThread.join();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted Exception");
                }
            }

            game.copy_newgrid();

            System.out.println("Gen " + (i + 1) + ": " + game.count_living());
        }

        long end = Instant.now().toEpochMilli();

        System.out.println(end - start);
    }
}