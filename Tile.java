public class Tile {
    private String displayIcon;
    private Collectible item;

    public Tile() {
        displayIcon = "⬛";
    }

    public Tile(String display) {
        displayIcon = display;
    }

    public Tile(Collectible i) {
        this();
        if (i != null) {
            displayIcon = i.getIcon();
            item = i;
        }
    }

    public String toString() {
        return displayIcon;
    }

    public Collectible getItem() {
        return item;
    }

    public boolean hasItem() {
        return item != null;
    }
}