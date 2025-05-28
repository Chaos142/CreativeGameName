public class Player {
    private int x;
    private static int boardWidth;

    private int health;
    private int integrity;
    private int buildItems;

    private long fogExpire;
    private long magnetExpire;
    private long webbedExpire;

    public Player(int length, int width) {
        boardWidth = width;
        x = boardWidth / 2;
        health = length;
        integrity = 0;
        buildItems = 0;

        fogExpire = 0;
        magnetExpire = 0;
        webbedExpire = 0;
    }

    public boolean moveLeft() {
        if (isWebbed() || health <= 0) return false;
        x = Math.max(0, x - 1);
        return true;
    }

    public boolean moveRight() {
        if (isWebbed() || health <= 0) return false;
        x = Math.min(x + 1, boardWidth - 1);
        return true;
    }

    public int pos() {
        return x;
    }

    public void damage() {
        health--;
    }

    public int getHealth() {
        return health;
    }

    public int getIntegrity() {
        return integrity;
    }

    public int getBuildItems() {
        return buildItems;
    }

    public void damage(int i) {
        health -= i;
    }

    public void addBuildItem() {
        buildItems++;
    }

    public boolean shouldShowFog() {
        return System.currentTimeMillis() < fogExpire;
    }

    public void fog() {
        fogExpire = System.currentTimeMillis() + 5000; // 5 seconds
    }

    public void getMagnet() {
        magnetExpire = System.currentTimeMillis() + 5000;
    }

    public boolean hasMagnet() {
        return System.currentTimeMillis() < magnetExpire;
    }

    public boolean isWebbed() {
        return System.currentTimeMillis() < webbedExpire;
    }

    public void web() {
        webbedExpire = System.currentTimeMillis() + 3000; // 3 seconds
    }

    public void breakDown() {
        integrity--;
    }

    public void delete() {
        x = -1;
    }

    public void heal() {
        health++;
    }

    public void repair() {
        integrity++;
    }

    public void spend() {
        buildItems--;
    }

    public void kill() {
        health = 0;
        buildItems = 0;
    }

    public void debug() {
        integrity = 19;
    }
}
