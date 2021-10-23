#include <stdio.h>
#include <stdlib.h>

#define MAX_SIZE 50
#define GEN_NUM 5
#define LIVING 1
#define DEAD 0

void init_grid(int **grid) {
    for (int i = 0; i < MAX_SIZE; i++) {
        for (int j = 0; j < MAX_SIZE; j++) {
            grid[i][j] = 0;
        }
    }

    //Glider
    int row = 1;
    int col = 1;
    grid[row][col + 1] = 1;
    grid[row + 1][col + 2] = 1;
    grid[row + 2][col] = 1;
    grid[row + 2][col + 1] = 1;
    grid[row + 2][col + 2] = 2;

    //R-pentonimo
    row = 10;
    col = 10;
    grid[row][col + 1] = 1;
    grid[row][col + 2] = 1;
    grid[row + 1][col] = 1;
    grid[row + 1][col + 1] = 1;
    grid[row + 2][col + 1] = 1;
}

void show_grid(int **mat) {
    for (int i = 0; i < MAX_SIZE; i++) {
        for (int j = 0; j < MAX_SIZE; j++) {
            if (mat[i][j] == 0) {
                printf("%c", 32);
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

    init_grid(grid);
    show_grid(grid);

    return 0;
}