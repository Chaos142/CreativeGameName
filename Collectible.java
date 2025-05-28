import java.util.ArrayList;

public class Collectible {
    private String icon;
    private boolean damageOnFall;
    private static ArrayList<Collectible> pool = new ArrayList<>();

    public Collectible(String i, boolean fallDamage) {
        icon = i;
        damageOnFall = fallDamage;

        pool.add(this);
    }

    public Collectible(String i, boolean fallDamage, int weight) {
        icon = i;
        damageOnFall = fallDamage;

        for (int j = 0; j < weight; j++) {
            pool.add(this);
        }
    }

    public Collectible(Collectible c) {
        damageOnFall = c.damageOnFall;
        icon = c.icon;
    }

    public String getIcon() {
        return icon;
    }

    public static Collectible getRandomCopy() {
        return new Collectible(pool.get((int) (Math.random() * pool.size())));
    }

    public boolean shouldDoDamage() {
        return damageOnFall;
    }
}