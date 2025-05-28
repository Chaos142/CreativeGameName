import java.util.Scanner;
public class Shop {
    private static String[] tooltips =
            {
                    "Note: The 'machine gun' is neither automatic nor particularly effective.",
                    "\"MIDDLE EAST MIDDLE EAST MIDDLE EAST MIDDLE EAST\" - Tyler Engola",
                    "One of the test subjects is named John TSN, odd name but you don't judge",
                    "\"Casting\" - Tyler Engola",
                    "During the development of this game, we spent 30 minutes debugging the method to catch falling items. The problem was that it was called catch().",
                    "TSN Chapter 2 is predicted to come out some time before the heat death of the universe",
                    "Is something supposed to go here?",
                    "The day on the log when you first launch the game comes from the [TEST SUBJECT NAME] social media."
            };

    private boolean isOpen;
    public Shop()
    {
        isOpen = false;
    }

    public void displayShop(Player p)
    {
        isOpen = true;
        System.out.print("\033[H\033[2J");

        System.out.println("███▓▒░░           [SHOP]          ░░▒▓███");
        System.out.println("=========================================");
        System.out.println("ˢʰᵒᵖ ˢᵖᵒⁿˢᵒʳᵉᵈ ᵇʸ ᵐᵘⁿᶜʰ ᶦⁿᶜ®");
        System.out.println();
        System.out.println("   [1] health pack     [2] repair kit    ");
        System.out.println();
        System.out.println("   [3] instructions    [4] quit game     ");
        System.out.println();
        System.out.println("🔧 : " + p.getBuildItems());
        System.out.println("=========================================");
        System.out.println(tooltips[(int) (Math.random() * tooltips.length)]);
    }

    public void closeShop() {
        System.out.print("\033[H\033[2J");
        isOpen = false;
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public void printInstructions() {
        Scanner in = new Scanner(System.in);
        System.out.println("\n[INITIALIZING CONTROL INTERFACE]");
        wait(2000);
        System.out.println("Use [A] to shift left. [D] to shift right.");
        wait(500);
        System.out.println("Manual input required. Press [ENTER] after each command.");
        System.out.println("\n[PRESS ENTER TO CONTINUE, OR E TO SKIP]");
        if (in.nextLine().equalsIgnoreCase("e")) return;

        System.out.println("[VITAL READOUTS]");
        wait(1000);
        System.out.println("◉  Left: [CHASSIS INTEGRITY] - if it hits 0, your core fails.");
        System.out.println("◉  Right: [TEST CHAMBER STABILITY] - restore this to reassert control.");
        wait(500);
        System.out.println("Both values will appear beneath the grid.");
        System.out.println("\n[PRESS ENTER TO CONTINUE]");
        in.nextLine();

        System.out.println("Fragments will begin to fall from above: scrap, tech, and corruption.");
        wait(500);
        System.out.println("Position yourself to intercept, dodge, or endure.");
        wait(500);
        System.out.println("Let too many payloads hit the floor, and you'll feel it.");
        System.out.println("\n[PRESS ENTER TO CONTINUE]");
        in.nextLine();

        System.out.println("[LOADING ITEM CATALOG]");
        wait(2000);
        System.out.println("🔧  | [WRENCH]: Currency. Collect to repair or heal. Damages if ignored.");
        System.out.println("🕒  | [CHRONO-FRAGMENT]: Slows drop speed. Fragile.");
        System.out.println("🧲  | [POLAR CORE]: Creates a magnetic field powerful enough to increase your pickup range, while obscuring the vision near you.");
        wait(1000);
        System.out.println("💣  | [BOMB SHELL]: Highly volatile. Impact = devastation.");
        System.out.println("🕸  | [CYBER-WEB]: Shuts down your movement for a few cycles.");
        System.out.println("👾  | [STRANGE EXPLOIT]: Obscures visual feed with fog.");
        System.out.println("💻  | [DENUVO CRACK]: TSN Anti-Piracy Screen.");
        wait(1000);
        System.out.println("🎰  | [UNKNOWN CONSTRUCT]: Results... unpredictable.");
        wait(500);
        System.out.println("\n[PRESS ENTER TO CONTINUE]");
        in.nextLine();

        System.out.println("Occasionally, you'll see a countdown.");
        wait(500);
        System.out.println("When it reaches zero: [SHOP INTERFACE] will unlock.");
        wait(500);
        System.out.println("Spend your wrenches - restore health, repair the chamber.");
        wait(500);
        System.out.println("The system won't let you leave with unspent parts.");
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}