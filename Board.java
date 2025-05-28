import java.util.ArrayList;
public class Board {
    private Tile[][] board;
    private Player player;

    private boolean[][] webbedTiles;

    public Board() {
        board = new Tile[20][15];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (r == board.length - 1) {
                    board[r][c] = new Tile("⬜");
                }
                else if (c == 1 || c == board[0].length - 2) {
                    board[r][c] = new Tile("🟧");
                }
                else {
                    board[r][c] = new Tile();
                }
            }
        }

        webbedTiles = new boolean[board.length][board[0].length];
    }

    public void addPlayer(Player p) {
        player = p;
    }

    // the emojis look REALLY ugly on windows so we might change it
    // nvm it looks clean on windows 11 its just garbage on windows 10

    public void disp(BoardStepper stepper, long shopTimer) {
        System.out.print("\033[H\033[2J");
        for (int r = 0; r < board.length; r++) {

            if (r >= -player.getHealth() + board.length) { // this expression will reverse the indices, so the bars fill from the bottom up instead of the top down
                System.out.print("❤️");
            }
            else {
                System.out.print("💔");
            }

            for (int c = 0; c < board[0].length; c++) {
                if (r == board.length - 2 && c == player.pos()) {
                    System.out.print(" 🤖");
                }
                else if (
                        player.hasMagnet() &&
                                (
                                        (r == board.length - 2 && (c == player.pos() - 1 || c == player.pos() + 1)) || // sides
                                                (r == board.length - 3 && (c == player.pos() || c == player.pos() - 1 || c == player.pos() + 1)) // above
                                )
                ) {
                    System.out.print(" ⚡");
                }
                else if (player.shouldShowFog() && r < board.length - 6) {
                    System.out.print(" 🟩");
                }
                else if (player.isWebbed() && !board[r][c].hasItem() && webbedTiles[r][c]) {
                    System.out.print(" 🕸");
                }
                else {
                    System.out.print(" " + board[r][c]);
                }
            }

            if (r >= -player.getIntegrity() + board.length) {
                System.out.print(" ⚙️");
            }
            else {
                System.out.print(" 🔥");
            }


            System.out.println();
        }

        if (shopTimer - System.currentTimeMillis() < 10000) {
            long remaining = shopTimer - System.currentTimeMillis();
            int seconds = (int) Math.ceil(remaining / 1000.0);
            System.out.println("Shop Opens In: " + Math.max(0, seconds));
        }
        System.out.println("Health: " + (int) Math.max(0, Math.ceil(100.0 * player.getHealth() / board.length)) + "%");
        System.out.println("Integrity: " + (int) Math.ceil(100.0 * player.getIntegrity() / board.length) + "%");
        System.out.println("Wrenches: " + player.getBuildItems());
        if (player.shouldShowFog()) {
            System.out.print("👾 ");
        }
        if (player.hasMagnet()) {
            System.out.print("🧲 ");
        }
        if (player.isWebbed()) {
            System.out.print("🕸 ");
        }
        else {
            webbedTiles = new boolean[board.length][board[0].length];
        }
        if (stepper.delay() == stepper.getSlowedDelay()) { // slowed delay
            System.out.print("🕒 ");
        }
        System.out.println();
    }

    public boolean fall() {
        boolean takeDamage = false;
        // damage when it hits ground
        for (int c = 2; c < board[0].length - 2; c++) {
            if (board[board.length - 2][c].hasItem() && board[board.length - 2][c].getItem().shouldDoDamage()) {
                takeDamage = true; // deal damage
                break;
            }
        }

        // shift down
        for (int r = board.length - 2; r > 0; r--) {
            for (int c = 0; c < board[0].length; c++) {
                board[r][c] = board[r-1][c];
            }
        }

        return takeDamage;
    }

    public ArrayList<Collectible> catchItem() {

        ArrayList<Collectible> caughtItems = new ArrayList<>();
        int pos = player.pos();
        if (board[board.length - 2][pos].hasItem()) {
            caughtItems.add(board[board.length - 2][pos].getItem());
            board[board.length - 2][pos] = new Tile();
        }
        if (player.hasMagnet()) {
            // left
            if (pos >= 3 && board[board.length - 2][pos - 1].hasItem() && board[board.length - 2][pos - 1].getItem().shouldDoDamage()) {
                caughtItems.add(board[board.length - 2][pos - 1].getItem());
                board[board.length - 2][pos - 1] = new Tile();
            }
            // right
            if (pos <= board[0].length - 4 && board[board.length - 2][pos + 1].hasItem() && board[board.length - 2][pos + 1].getItem().shouldDoDamage()) {
                caughtItems.add(board[board.length - 2][pos + 1].getItem());
                board[board.length - 2][pos + 1] = new Tile();
            }
            // top
            if (pos >= 2 && board[board.length - 3][pos].hasItem() && board[board.length - 3][pos].getItem().shouldDoDamage()) {
                caughtItems.add(board[board.length - 3][pos].getItem());
                board[board.length - 3][pos] = new Tile();
            }
            // top left
            if (pos >= 3 && board[board.length - 3][pos - 1].hasItem() && board[board.length - 3][pos - 1].getItem().shouldDoDamage()) {
                caughtItems.add(board[board.length - 3][pos - 1].getItem());
                board[board.length - 3][pos - 1] = new Tile();
            }
            // top right
            if (pos <= board[0].length - 4 && board[board.length - 3][pos + 1].hasItem() && board[board.length - 3][pos + 1].getItem().shouldDoDamage()) {
                caughtItems.add(board[board.length - 3][pos + 1].getItem());
                board[board.length - 3][pos + 1] = new Tile();
            }
        }

        return caughtItems;
    }

    public void cleanup() {
        // clean up top
        for (int c = 2; c < board[0].length - 2; c++) {
            board[0][c] = new Tile();
        }

        // clean up bottom
        for (int c = 2; c < board[0].length - 2; c++) {
            board[board.length - 1][c] = new Tile("⬜");
        }
    }

    // spawns collectible i at random column between the lava
    public void spawnCollectible(Collectible i) {
        board[0][(int)(Math.random() * (board[0].length - 4)) + 2] = new Tile(i);
    }

    public void spawnCollectible(Collectible i, int c) {
        board[1][c] = new Tile(i);
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void dispExplosion(int r, int c) {
        board[r][c] = new Tile("💥");
    }

    public void burn() {
        int fires = (board.length * board[0].length) / 30; // roughly 3% of the board
        for (int i = 0; i < fires; i++) {
            int r = (int) (Math.random() * board.length);
            int c = (int) (Math.random() * board[0].length);

            board[r][c] = new Tile("🔥");
            Shop.wait(10);
        }
    }

    public void populateWebs() {
        for (int r = 0; r < webbedTiles.length - 1; r++) {
            for (int c = 0; c < webbedTiles[0].length; c++) {
                if (webbedTiles[r][c]) return;
            }
        }
        for (int r = 0; r < webbedTiles.length - 1; r++) {
            for (int c = 0; c < webbedTiles[0].length; c++) {
                if (Math.random() < .25) {
                    webbedTiles[r][c] = true;
                }
            }
        }
    }
}