public class Game {
    private Board board;
    private int maxIntegrity;
    private Player player;
    private Shop shop;
    
    private BoardStepper stepper;
    
    private long shopOpenTimer;
    
    private Collectible bomb;
    
    public Game() {
        // CREATE COLLECTIBLES HERE (feel free to add as many as you want for now)
        // these are just ideas
        
        new Collectible("🔧", true, 2);
        new Collectible("🕒", false);
        new Collectible("🧲", false);
        
        bomb = new Collectible("💣", false, 3);
        new Collectible("🕸", false);
        new Collectible("👾", false);
        new Collectible("💻", false);
        
        new Collectible("🎰", false);
        
        
        board = new Board();
        shop = new Shop();
        maxIntegrity = board.getBoard().length;
        player = new Player(board.getBoard().length, board.getBoard()[0].length);
        board.addPlayer(player);
        
        shopOpenTimer = System.currentTimeMillis() + (int) (Math.random() * 30001) + 30000;
    }
    
    public void addStepper(BoardStepper s) {
        stepper = s;
    }
    
    public void step(BoardStepper stepper) {
        if (shop.isOpen()) return;
        
        if (shopOpenTimer - System.currentTimeMillis() < 10000 && player.getBuildItems() == 0) {
            shopOpenTimer += 10000;
        }
        else if (System.currentTimeMillis() >= shopOpenTimer) {
            shop.displayShop(player);
            System.out.println("Health: " + (int) Math.max(0, Math.ceil(100.0 * player.getHealth() / board.getBoard().length)) + "%");
            System.out.println("Integrity: " + (int) Math.ceil(100.0 * player.getIntegrity() / board.getBoard().length) + "%");
            reformBoard();
            return;
        }
        if (board.fall()) {
            player.damage();
        }
        handleCaughtItems();
        board.cleanup();
        
        // this probably wont happen on every turn to make it more balanced
        board.spawnCollectible(Collectible.getRandomCopy());
        
        board.disp(stepper, shopOpenTimer);
        
        if (player.getHealth() <= 0) {
            board.dispExplosion(board.getBoard().length - 2, player.pos());
            player.delete();
            while (player.getIntegrity() > 0) {
                player.breakDown();
                board.disp(stepper, shopOpenTimer);
                Shop.wait(200);
            }
            for (int i = 0; i < 10; i++) {
                board.burn();
                board.disp(stepper, shopOpenTimer);
                Shop.wait(200);
            }
            System.out.println("\"How can you be this bad at literally just strafing?\" - Tyler Engola");
            System.exit(0);
        }
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Shop getShop()
    {
        return shop;
    }
    
    public void itemExecution(String itemType) {
        switch (itemType) {
            case "🔧":
                player.addBuildItem();
                break;
            case "💣":
                player.damage(6);
                board.dispExplosion(board.getBoard().length - 1, player.pos());
                break;
            case "👾":
                player.fog();
                break;
            case "🧲":
                player.getMagnet();
                break;
            case "🕸":
                player.web();
                board.populateWebs();
                break;
            case "🕒":
                stepper.triggerSlow();
                break;
            case "💻":
                int safeSquare = (int)(Math.random() * (board.getBoard()[0].length - 4)) + 2;
                for (int c = 0; c < board.getBoard()[0].length; c++) {
                    if (c != safeSquare) {
                        board.spawnCollectible(bomb, c);
                    }
                }
                break;
            case "🎰":
                itemExecution(Collectible.getRandomCopy().getIcon());
                break;
        }
    }
    
    public Board getBoard() {
        return board;
    }
    
    public int getMaxIntegrity() {
        return maxIntegrity;
    }
    
    public void handleCaughtItems() {
        for (Collectible caughtItem : board.catchItem()) {
            if (caughtItem != null) {
                String itemType = caughtItem.getIcon();
                itemExecution(itemType);
            }
        }
    }
    
    public void reformBoard() {
        board = new Board();
        board.addPlayer(player);
    }
    
    public long shopOpenTimer() {
        return shopOpenTimer;
    }
    
    public void addMoreWrenches(int w) {
        new Collectible("🔧", true, w);
    }
}
