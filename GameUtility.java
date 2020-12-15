package battleship;

import java.util.ArrayList;
import java.util.Scanner;

public class GameUtility {

    private static final Player PLAYER1 = new Player();
    private static final Player PLAYER2 = new Player();
    private static boolean player1Turn = true;
    private static final GameUtility INSTANCE = new GameUtility();
    private final Scanner scanner = new Scanner(System.in);
    private GameUtility() {

    }

    public static GameUtility getInstance() {
        return INSTANCE;
    }

    public void startGame() {
        System.out.println("Player 1, place your ships on the game field");
        PLAYER1.getGameBoard().printBoard();
        PLAYER1.placeShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        System.out.println("Player 2, place your ships to the game field");
        PLAYER2.getGameBoard().printBoard();
        PLAYER2.placeShips();
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
        boolean gameFinished;

        do {
            if (player1Turn) {
                PLAYER1.getMarkerBoard().printBoard();
                System.out.println("---------------------");
                PLAYER1.getGameBoard().printBoard();
                System.out.println("Player 1, it's your turn:");
                gameFinished = PLAYER1.doShot();
            } else {
                PLAYER2.getMarkerBoard().printBoard();
                System.out.println("---------------------");
                PLAYER2.getGameBoard().printBoard();
                System.out.println("Player 2, it's your turn:");
                gameFinished = PLAYER2.doShot();
            }
            player1Turn = !player1Turn;
            System.out.println("Press Enter and pass the move to another player");
            scanner.nextLine();
        } while (!gameFinished);

    }

    public static boolean checkShot(int row, int col) {
        boolean hit = false;
        boolean sank = false;
        Player player = player1Turn ? PLAYER1 : PLAYER2;
        Player enemy = player1Turn ? PLAYER2 : PLAYER1;
        ArrayList<ArrayList<String>> shipLocations = enemy.getGameBoard().getLocations();
        String loc = row + "" + col;

        if (enemy.getGameBoard().getGrid()[row][col] == 'O' || enemy.getGameBoard().grid[row][col] == 'X') {
            hit = true;
        }

        for (ArrayList<String> locations : shipLocations) {
            if (locations.contains(loc)) {
                locations.remove(loc);
                enemy.getGameBoard().reduceShipCount();
                if (locations.isEmpty()) {
                    enemy.getGameBoard().getLocations().remove(locations);
                    sank = true;
                }
                break;
            }
        }

        if (enemy.getGameBoard().isShipEmpty()) {
            player.getMarkerBoard().addMarker(row, col, 'X');
            enemy.getGameBoard().addMarker(row, col, 'X');
            System.out.println("You sank the last ship. You won. Congratulations!");
            return true;
        }
        if (hit) {
            player.getMarkerBoard().addMarker(row, col, 'X');
            enemy.getGameBoard().addMarker(row, col, 'X');
            if (sank) {
                System.out.println("You sank a ship!");
            } else {
                System.out.println("You hit a ship!");
            }
        } else {
            player.getMarkerBoard().addMarker(row, col, 'M');
            enemy.getGameBoard().addMarker(row, col, 'M');
            System.out.println("You missed!");
        }
        return false;
    }

    public static boolean checkInput(Board gameBoard, int startRow, int startCol, int endRow, int endCol, Ship ship) {

        char[][] grid = gameBoard.getGrid();

        if (startRow != endRow && startCol != endCol) {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }

        int length = startRow != endRow ? Math.abs(startRow - endRow) : Math.abs(startCol - endCol);

        if (length + 1 != ship.getCount()) {
            System.out.println("Error! Wrong length of the " + ship.getDescription() + "! Try again:");
            return false;
        }

        if (checkNearbyCells(grid, startRow, startCol, endRow, endCol)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }
        return true;
    }

    private static boolean checkNearbyCells(char[][] grid, int startRow, int startCol, int endRow, int endCol) {
        boolean isHorizontal = startRow == endRow;
        int start;
        int end;
        int count;
        if (isHorizontal) {
            start = startCol > 0 ? startCol - 1 : startCol;
            end = endCol < 9 ? endCol + 1 : endCol;
            count = startRow == 0 || startRow == 9 ? 2 : 3;
        } else {
            start = startRow > 0 ? startRow - 1 : startRow;
            end = endRow < 9 ? endRow + 1 : endRow;
            count = startCol == 0 || startCol == 9 ? 2 : 3;
        }

        if (startRow > endRow || startCol > endCol) {
            start += -1;
            end += 1;
        }

        int loopStart;

        if (isHorizontal) {
            if (startRow == 0) {
                loopStart = startRow;
            } else {
                loopStart = startRow - 1;
            }
        } else {
            if (startCol == 0) {
                loopStart = startCol;
            } else {
                loopStart = startCol - 1;
            }
        }

        for (int i = loopStart; i < loopStart + count; i++) {
            for (int k = start; k <= end; k++) {
                char c = isHorizontal ? grid[i][k] : grid[k][i];
                if (c != '~') {
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] changeCoor(char row, int col) {
        int[] newCoor = new int[2];
        newCoor[0] = row - 'A';
        newCoor[1] = col - 1;
        return newCoor;
    }
}
