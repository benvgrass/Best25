package temp;

import java.util.Comparator;

/**
 * Pair of players that is valid to serve as edge for our 'graph'
 */
public class PlayerPair {
    private Player p1;
    private Player p2;

    public PlayerPair(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double getWAR() {
        return p1.getWAR() + p2.getWAR();
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public static Comparator<PlayerPair> comparator = Comparator.comparing(PlayerPair::getWAR).reversed();

}
