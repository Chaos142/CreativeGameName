import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


// file is long_time String_name String_date
public class Leaderboard
{
    private File file;

    public Leaderboard() {
        try {
            file = new File("Leaderboard.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void append(long time, String name, String date) throws FileNotFoundException {
        Scanner in = new Scanner(file);

        int appendIndex = findSortedIndex(time);
        String before = "";
        String after = "";
        for (int i = 0; i < appendIndex; i++) {
            before += in.nextLine() + "\n";
        }
        for (int i = appendIndex; in.hasNextLine(); i++) {
            after += in.nextLine() + "\n";
        }
        in.close();
        PrintWriter out = new PrintWriter(file);
        out.print(before);
        out.println(time + " " + name + " " + date);
        out.print(after);
        out.close();
    }

    public String toString() {
        Scanner in = null;
        try {
            in = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String s = "[LEADERBOARD]\n===================================\n";
        int place = 1;
        while (in.hasNextLine() && place <= 5) { // top 5
            long time = in.nextLong();
            String name = in.next();
            String date = in.next();
            in.nextLine();

            s += "[" + place + "]: " + name + " (" + date + ") - " + new TimeFormat(time).toString() + "\n";
            place++;
        }
        in.close();
        return s;
    }

    public int findSortedIndex(long target) throws FileNotFoundException {
        Scanner in = new Scanner(file);
        int index = 0;
        while (in.hasNextLine()) {

            long time = in.nextLong();

            if (time > target) {
                return index;
            }

            in.nextLine();
            index++;
        }
        in.close();
        return index;
    }
}
