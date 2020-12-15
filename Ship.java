package battleship;

public class Ship {
    private String description;
    private int count;

    public Ship(String description, int count) {
        this.description = description;
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }
}
