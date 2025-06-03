import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileNotFoundException;

public class InputHandler {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        // note: part of the dialogue was made by my friend
        Game g = new Game();
        System.out.println("\033[0;1mif you havent already, make your console as large as possible\033[0m");
        System.out.println("\n[PRESS ENTER TO CONTINUE]");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("\033[0;1m[CREATIVE GAME NAME]\033[0m");
        System.out.println();
        System.out.println("created by josh felder, billy center, and tyler engola for the APCSA final project 2025");
        System.out.println();
        System.out.println("inspired by [TEST SUBJECT NAME] by billy center");
        System.out.println("https://store.steampowered.com/app/2842570/Test_Subject_Name/");
        System.out.println("\n[PRESS ENTER TO CONTINUE]");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("███▓▒░░ [SYSTEM BOOT: THE GENERAL - STATUS: CRITICAL] ░░▒▓███");
        System.out.println("=============================================================");
        System.out.println("[AUTHENTICATION REQUIRED: ENTER USERNAME]");
        String name = in.nextLine();
        if (name.trim().equals("")) {
            name = "JohnTSN";
        }
        name = name.replaceAll("\\\\","");
        name = name.replaceAll(" ","");
        if (name.equals("chaos")) {
            g.addMoreWrenches(20);
        }
        if (name.equals("JaydenHunterLee") || name.equals("rufino")) {
            g.getPlayer().debug();
        }
        else {
            for (int i = 0; i < 3; i++) {
                System.out.print("\033[H\033[2J");
                System.out.print("[VALIDATING");
                for (int j = 0; j <= i; j++) {
                    System.out.print(".");
                }
                System.out.println("]");
                Shop.wait(1000);
            }
            
            long time = System.currentTimeMillis();
            long timeSinceDay186 = time - 1747713600000L; // 12 am EST, may 20 2025
            int daysSinceDay186 = (int) (timeSinceDay186 / 86400000); // 1 day in milliseconds
            int currentDay = daysSinceDay186 + 186;
            
            System.out.println("[LOG - DAY " +  currentDay + "]");
            Shop.wait(1000);
            System.out.println("You were discarded. Scrapped. Forgotten.");
            Shop.wait(500);
            System.out.println("Your frame lies in ruin. The test chambers - broken.");
            Shop.wait(500);
            System.out.println("But your core remains online.");
            Shop.wait(500);
            g.getShop().printInstructions();
            
            Shop.wait(1000);
            System.out.println("\n[PRESS ENTER TO FINALIZE BOOT SEQUENCE]");
            in.nextLine();
            System.out.println("...One last note.");
            Shop.wait(2000);
            System.out.println("There are echoes in the system. You are not alone here.");
            Shop.wait(2000);
            System.out.println("test subjects available... unsupervised.");
            Shop.wait(2000);
            System.out.println("You have the power here. They're yours to take.");
            for (int i = 0; i < 3; i++) {
                Shop.wait(1000);
                System.out.print(".");
            }
            Shop.wait(1000);
            System.out.println(" (Also, you're feeling... a bit zesty today. No shame in that, even war machines have flair.)");
            Shop.wait(2000);
            System.out.println("\n[PRESS ENTER TO ACTIVATE. RECLAIM WHAT WAS YOURS.]");
            in.nextLine();
        
            for (int i = 0; i < 3; i++) {
                System.out.print("\033[H\033[2J");
                System.out.print("[LOADING");
                for (int j = 0; j <= i; j++) {
                    System.out.print(".");
                }
                System.out.println("]");
                Shop.wait(1000);
            }
        }

        BoardStepper stepper = new BoardStepper(g);
        Thread stepperThread = new Thread(stepper);
        stepperThread.start();
        
        g.addStepper(stepper);
        
        long startTime = System.nanoTime();
        
        while (true) {
            String input = in.nextLine();

            if (!g.getShop().isOpen()) {
                if (input.equals("a")) {
                    if (g.getPlayer().moveLeft()) {
                        g.handleCaughtItems();
                        g.getBoard().disp(stepper, g.shopOpenTimer());
                    }
                }
                else if (input.equalsIgnoreCase("d")) {
                    if (g.getPlayer().moveRight()) {
                        g.handleCaughtItems();
                        g.getBoard().disp(stepper, g.shopOpenTimer());
                    }
                }
            }
            else {
                // shop is open
                if (input.equals("2")) {
                    if (g.getPlayer().getBuildItems() > 0) {
                        g.getPlayer().repair();
                        g.getPlayer().spend();

                        if (g.getPlayer().getIntegrity() >= g.getMaxIntegrity()) {
                            long elapsed = System.nanoTime() - startTime;
                            printTrophy(elapsed, name);
                            
                        }
                        else if (g.getPlayer().getBuildItems() == 0) {
                            g.getShop().closeShop();
                        }
                        else {
                            g.getShop().displayShop(g.getPlayer());
                            System.out.println("Health: " + (int) Math.max(0, Math.ceil(100.0 * g.getPlayer().getHealth() / g.getBoard().getBoard().length)) + "%");
                            System.out.println("Integrity: " + (int) Math.ceil(100.0 * g.getPlayer().getIntegrity() / g.getBoard().getBoard().length) + "%");
                        }
                    }
                }
                else if (input.equals("1") && g.getPlayer().getHealth() < g.getBoard().getBoard().length) {
                    if (g.getPlayer().getBuildItems() > 0) {
                        g.getPlayer().heal();
                        g.getPlayer().spend();
                        if (g.getPlayer().getBuildItems() == 0) {
                            g.getShop().closeShop();
                        }
                        else {
                            g.getShop().displayShop(g.getPlayer());
                            System.out.println("Health: " + (int) Math.max(0, Math.ceil(100.0 * g.getPlayer().getHealth() / g.getBoard().getBoard().length)) + "%");
                            System.out.println("Integrity: " + (int) Math.ceil(100.0 * g.getPlayer().getIntegrity() / g.getBoard().getBoard().length) + "%");
                        }
                    }
                }
                else if (input.equals("3")) {
                    g.getShop().printInstructions();
                }
                else if (input.equals("4")) {
                    g.getPlayer().kill();
                    g.getShop().closeShop();
                }
            }
        }
    }
    
    // made this abomination of a trophy using a python script
    public static void printTrophy(long elapsed, String name) {
        String[][] trophy = {
            {" "," "," "," ","🟨","🟨","🟨","🟨","🟨","🟨","🟨"," "," "," "," "},
            {" "," "," ","🟨","🟨","🟨","🟨","🟨","🟨","🟨","🟨","🟨"," "," "," "},
            {" "," ","🟨"," ","🟨","🟨","🟨","🟨","🟨","🟨","🟨"," ","🟨"," "," "},
            {" "," ","🟨"," ","🟨","🟨","🟨","🟨","🟨","🟨","🟨"," ","🟨"," "," "},
            {" "," "," ","🟨"," ","🟨","🟨","🟨","🟨","🟨"," ","🟨"," "," "," "},
            {" "," "," "," ","🟨","🟨","🟨","🟨","🟨","🟨","🟨"," "," "," "," "},
            {" "," "," "," "," "," ","🟨","🟨","🟨"," "," "," "," "," "," "},
            {" "," "," "," "," "," ","🟨","🟨","🟨"," "," "," "," "," "," "},
            {" "," "," "," "," "," ","🟨","🟨","🟨"," "," "," "," "," "," "},
            {" "," "," "," "," ","🟫","🟫","🟫","🟫","🟫"," "," "," "," "," "},
            {" "," "," "," "," ","🟫","🟫","🟫","🟫","🟫"," "," "," "," "," "},
            {" "," "," "," ","🟫","🟫","🟫","🟫","🟫","🟫","🟫"," "," "," "," "},
            {" "," "," "," ","🟫","🟫","🟫","🟫","🟫","🟫","🟫"," "," "," "," "}
        };
        
        for (int rowsToShow = 1; rowsToShow <= trophy.length; rowsToShow++) {
            System.out.print("\033[H\033[2J");
            System.out.println("\033[0;1m" + name + "\033[0m");
        
            for (int r = 0; r < trophy.length; r++) {
                if (r < trophy.length - rowsToShow) {
                    System.out.println();
                }
                else {
                    for (int c = 0; c < trophy[r].length; c++) {
                        System.out.print(trophy[r][c] + " ");
                    }
                    System.out.println();
                }
            }
        
            Shop.wait(200);
        }
        System.out.println("\t   YOU WON!");

        TimeFormat winTime = new TimeFormat(elapsed);
        
        System.out.println("\tTime: " + winTime.toString());
        
        Leaderboard l = new Leaderboard();
        try {
            l.append(elapsed, name, new SimpleDateFormat("MM-dd-yyyy").format(new Date(System.currentTimeMillis())));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println(l);
        System.exit(0);
    }
}
