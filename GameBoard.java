package battleship;

import java.util.ArrayList;

public class GameBoard extends Board {
    private int shipCount;
    private final ArrayList<String> AIRCRAFT_LOC;
    private final ArrayList<String> BATTLESHIP_LOC;
    private final ArrayList<String> SUBMARINE_LOC;
    private final ArrayList<String> CRUISER_LOC;
    private final ArrayList<String> DESTROYER_LOC;

    public GameBoard() {
        this.AIRCRAFT_LOC = new ArrayList<>();
        this.BATTLESHIP_LOC = new ArrayList<>();
        this.SUBMARINE_LOC = new ArrayList<>();
        this.CRUISER_LOC = new ArrayList<>();
        this.DESTROYER_LOC = new ArrayList<>();
    }

    public ArrayList<ArrayList<String>> getLocations() {
        ArrayList<ArrayList<String>> locations = new ArrayList<>();
        locations.add(AIRCRAFT_LOC);
        locations.add(BATTLESHIP_LOC);
        locations.add(SUBMARINE_LOC);
        locations.add(CRUISER_LOC);
        locations.add(DESTROYER_LOC);
        return locations;
    }

    public void shipHit(int row, int col) {
        System.out.println("Hit ship called");
        grid[row][col] = 'X';
        shipCount--;
    }

    public void addLocation(String loc, String shipType) {
        if (shipType.equals("Aircraft Carrier")) {
            AIRCRAFT_LOC.add(loc);
        } else if (shipType.equals("Battleship")) {
            BATTLESHIP_LOC.add(loc);
        } else if (shipType.equals("Submarine")) {
            SUBMARINE_LOC.add(loc);
        } else if (shipType.equals("Cruiser")) {
            CRUISER_LOC.add(loc);
        } else {
            DESTROYER_LOC.add(loc);
        }
        shipCount++;
    }

    public void reduceShipCount() {
        shipCount--;
    }

    public boolean isShipEmpty(){
        return shipCount == 0;
    }
}
