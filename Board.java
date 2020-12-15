package battleship;

public class Board {
    protected char[][] grid;

    public Board() {
        this.grid = new char[10][10];
        fillGrid();
    }

    private void fillGrid() {
        for (int i = 0; i < 10; i++) {
            for (int k = 0; k < 10; k++) {
                grid[i][k] = '~';
            }
        }
    }

    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < grid.length; i++) {
            System.out.print((char) (65 + i) + " ");
            for (int k = 0; k < grid.length; k++) {
                System.out.print(grid[i][k] + " ");
            }
            System.out.println();
        }
    }

    public void addMarker(int row, int col, char marker) {
        grid[row][col] = marker;
    }

    public char[][] getGrid() {
        return grid;
    }
}
