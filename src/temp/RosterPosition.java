package temp;

import java.util.LinkedList;
import java.util.List;

/**
 * Serves as Position on a roster
 */
public class RosterPosition {
    private final int max;
    private int numPlayers;
    private LinkedList<Player> players;
    public RosterPosition(int max) {
        this.max = max;
        this.players = new LinkedList<>();
        this.numPlayers = 0;
    }

    public double getWAR() {
        double sum = 0;
        for (Player player : players) sum += player.getWAR();
        return sum;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean addPlayer(Player player) {
        if (numPlayers < max) {
            players.add(player);
            numPlayers++;
            return true;
        }
        return false;
    }

    public boolean removePlayer(Player p) {

        if (players.remove(p)) {
            numPlayers--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "RosterPosition{" +
                "max=" + max +
                ", numPlayers=" + numPlayers +
                ", players=" + players +
                ", war=" + getWAR() +
                '}';
    }
}
