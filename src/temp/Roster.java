package temp;

import java.util.*;

/**
 * Our Roster will serve as our 'graph' of sorts.
 * We Want our maximal spanning roster: a roster that fills all positions with maximal value.
 * Our 'edges' will be the sum of the two players WARs.
 * In reality, if a player is on the roster, the maximal edge that can be used and isn't connected,
 * is a player that's position isn't yet filled.
 */
public class Roster {
	
	public List<Player> players;
	
	public Roster () {
		players = new LinkedList<>();
	}

	public double getWar() {
		double sum = 0;
		for (Player p: players) sum += p.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {
	    // TODO: implement
	    return false;
	}

	public List<Player> getPlayers() {
        return players;
	}


	@Override
	public String toString() {
		return getPlayers().toString();
	}

}
