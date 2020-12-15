package battleship;

import java.util.Scanner;

public class Player {

    private final GameBoard GAME_BOARD;
    private final Board MARKER_BOARD;

    public Player() {
        GAME_BOARD = new GameBoard();
        MARKER_BOARD = new Board();
    }

    public void placeShips() {
        Scanner scanner = new Scanner(System.in);
        String[] input;
        String from;
        String to;
        char startRow;
        char endRow;
        int startCol;
        int endCol;
        Ship[] ships = {new AircraftCarrier(), new BattleShip(), new Submarine(), new Cruiser(), new Destroyer()};
        for (int i = 0; i < ships.length; i++) {
            System.out.println("Enter the coordinates of the " + ships[i].getDescription() + " (" + ships[i].getCount() + " cells):");
            while (true) {
                try {
                    input = scanner.nextLine().split(" ");
                    from = input[0];
                    to = input[1];
                    startRow = from.substring(0, 1).charAt(0);
                    endRow = to.substring(0, 1).charAt(0);
                    startCol = Integer.parseInt(from.substring(1));
                    endCol = Integer.parseInt(to.substring(1));
                } catch (Exception e) {
                    System.out.println("Error!");
                    continue;
                }
                int[] newStartCoor = GameUtility.changeCoor(startRow, startCol);
                int[] newEndCoor = GameUtility.changeCoor(endRow, endCol);
                int newStartRow = newStartCoor[0];
                int newStartCol = newStartCoor[1];
                int newEndRow = newEndCoor[0];
                int newEndCol = newEndCoor[1];
                if (GameUtility.checkInput(GAME_BOARD, newStartRow, newStartCol, newEndRow, newEndCol, ships[i])) {
                    int loopStart;
                    int loopEnd;
                    boolean isHorizontal = newStartRow == newEndRow;
                    if (newStartRow != newEndRow) {
                        loopStart = Math.min(newStartRow, newEndRow);
                        loopEnd = Math.max(newStartRow, newEndRow);
                    } else {
                        loopStart = Math.min(newStartCol, newEndCol);
                        loopEnd = Math.max(newStartCol, newEndCol);
                    }
                    for (int k = loopStart; k <= loopEnd; k++) {
                        if (isHorizontal) {
                            GAME_BOARD.addMarker(newStartRow, k, 'O');
                            GAME_BOARD.addLocation(newStartRow + "" + k, ships[i].getDescription());
                        } else {
                            GAME_BOARD.addMarker(k, newStartCol, 'O');
                            GAME_BOARD.addLocation(k + "" + newStartCol, ships[i].getDescription());
                        }
                    }
                    break;
                }
            }
            GAME_BOARD.printBoard();
        }
    }

    public boolean doShot() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                char row = input.charAt(0);
                int col = Integer.parseInt(input.substring(1));
                if (row > 'A' + 9 || row < 'A' || col > 10 || col < 1) {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                }
                int[] newCoor = GameUtility.changeCoor(row, col);
                int newRow = newCoor[0];
                int newCol = newCoor[1];
                return GameUtility.checkShot(newRow, newCol);
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
    }

    public GameBoard getGameBoard() {
        return GAME_BOARD;
    }

    public Board getMarkerBoard() {
        return MARKER_BOARD;
    }
}
