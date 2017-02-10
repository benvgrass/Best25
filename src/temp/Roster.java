package temp;

import java.util.*;

public class Roster {
	
	private HashMap<Integer, Player> players;

	public Roster () {
		players = new HashMap<>();
	}

	public double getWar() {
		double sum = 0;
		for (Player p: players.values()) sum += p.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {
		return players.putIfAbsent(p.getfID(), p) == null;
	}

	public Collection<Player> getPlayers() {
		Collection<Player> players = new TreeSet<Player>(Comparator.comparing(Player::getSeason)) {
			@Override
			public String toString() {
				Iterator<Player> it = iterator();
				if (! it.hasNext())
					return "";

				StringBuilder sb = new StringBuilder();
				for (;;) {
					Player e = it.next();
					sb.append(e);
					if (! it.hasNext())
						return sb.toString();
					sb.append('\n');
				}
			}

		};
		players.addAll(this.players.values());
		return players;
	}

	public boolean removePlayer(Player p) {
		return players.remove(p.getfID(), p);
	}

	@Override
	public String toString() {
		return getPlayers().toString();
	}

}
