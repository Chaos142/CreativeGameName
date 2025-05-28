class BoardStepper implements Runnable { // tyler doesnt like the name of this class because hes a
    private Game g;

    private int delay;
    private int slowedDelay;
    private long slowedExpiration;

    public BoardStepper(Game g) {
        this.g = g;
        delay = 500;
        slowedDelay = delay * 2;
    }

    public void run() {
        while (true) {
            if (!g.getShop().isOpen()) {
                g.step(this);
            }

            Shop.wait(delay());
        }
    }

    public int delay() {
        if (System.currentTimeMillis() < slowedExpiration) {
            return slowedDelay;
        }
        return delay;
    }

    public void triggerSlow() {
        slowedExpiration = System.currentTimeMillis() + 5000;
    }

    public int getNormalDelay() {
        return delay;
    }

    public int getSlowedDelay() {
        return slowedDelay;
    }
}
