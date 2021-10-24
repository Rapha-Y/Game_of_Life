#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 50
#define GEN_NUM 5
#define LIVING 1
#define DEAD 0

void init_grid(int **grid) {
    for (int i = 0; i < MAX_SIZE; i++) {
        for (int j = 0; j < MAX_SIZE; j++) {
            grid[i][j] = DEAD;
        }
    }

    //Glider
    int row = LIVING;
    int col = LIVING;
    grid[row][col + 1] = LIVING;
    grid[row + 1][col + 2] = LIVING;
    grid[row + 2][col] = LIVING;
    grid[row + 2][col + 1] = LIVING;
    grid[row + 2][col + 2] = LIVING;

    //R-pentonimo
    row = 10;
    col = 10;
    grid[row][col + 1] = LIVING;
    grid[row][col + 2] = LIVING;
    grid[row + 1][col] = LIVING;
    grid[row + 1][col + 1] = LIVING;
    grid[row + 2][col + 1] = LIVING;
}

int getNeighbors(int **grid, int i, int j) {
    int prev_i = i - 1;
    int prev_j = j - 1;
    int next_i = i + 1;
    int next_j = j + 1;

    //loop through the board's edges
    if (prev_i < 0) {
        prev_i = MAX_SIZE - 1;
    }
    if (prev_j < 0) {
        prev_j = MAX_SIZE - 1;
    }
    if (next_i > MAX_SIZE - 1) {
        next_i = 0;
    }
    if (next_j > MAX_SIZE - 1) {
        next_j = 0;
    }

    //count number of living neighbors
    int neighbor_num = 0;
    if (grid[prev_i][prev_j] == LIVING) {
        neighbor_num++;
    }
    if (grid[prev_i][j] == LIVING) {
        neighbor_num++;
    }
    if (grid[prev_i][next_j] == LIVING) {
        neighbor_num++;
    }
    if (grid[i][prev_j] == LIVING) {
        neighbor_num++;
    }
    if (grid[i][next_j] == LIVING) {
        neighbor_num++;
    }
    if (grid[next_i][prev_j] == LIVING) {
        neighbor_num++;
    }
    if (grid[next_i][j] == LIVING) {
        neighbor_num++;
    }
    if (grid[next_i][next_j] == LIVING) {
        neighbor_num++;
    }

    return neighbor_num;
}

void fill_newgrid(int **grid, int **newgrid) {
    for (int i = 0; i < MAX_SIZE; i++) {
        for (int j = 0; j < MAX_SIZE; j++) {
            int neighbor_num = getNeighbors(grid, i, j);

            if (grid[i][j] == LIVING && (neighbor_num < 2 || neighbor_num > 3)) {
                newgrid[i][j] == DEAD;
            } else if (grid[i][j] == DEAD && neighbor_num == 3) {
                newgrid[i][j] = LIVING;
            } else {
                newgrid[i][j] = grid[i][j];
            }
        }
    }
}

void show_grid(int **mat) {
    for (int i = 0; i < MAX_SIZE; i++) {
        for (int j = 0; j < MAX_SIZE; j++) {
            if (mat[i][j] == DEAD) {
                printf("%c", 176);
            } else {
                printf("%c", 219);
            }
        }
        printf("\n");
    }
}

int main() {
    int **grid = (int**) malloc(MAX_SIZE * sizeof(int*));
    for (int i = 0; i < MAX_SIZE; i++) {
        grid[i] = (int*) malloc(MAX_SIZE * sizeof(int));
    }

    int **newgrid = (int**) malloc(MAX_SIZE * sizeof(int*));
    for (int i = 0; i < MAX_SIZE; i++) {
        newgrid[i] = (int*) malloc(MAX_SIZE * sizeof(int));
    }

    init_grid(grid);
    show_grid(grid);

    printf("\n");

    fill_newgrid(grid, newgrid);
    show_grid(newgrid);

    return 0;
}