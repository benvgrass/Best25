package temp;

import java.util.*;

public class Roster {
	
	private HashMap<Position, RosterPosition> available;
	private HashMap<Integer, Integer> playerIDs;

	public Roster () {
		available = new HashMap<>();
		available.put(Position.C, new RosterPosition(2));
		available.put(Position.B1, new RosterPosition(1));
		available.put(Position.B2, new RosterPosition(1));
		available.put(Position.B3, new RosterPosition(1));
		available.put(Position.SS, new RosterPosition(2));
		available.put(Position.LF, new RosterPosition(1));
		available.put(Position.CF, new RosterPosition(1));
		available.put(Position.RF, new RosterPosition(1));
		available.put(Position.OF, new RosterPosition(2));
		available.put(Position.SP, new RosterPosition(5));
		available.put(Position.RP, new RosterPosition(7));
		available.put(Position.DH, new RosterPosition(1));
		playerIDs = new HashMap<>();
	}
	
	public double getWar() {
		int sum = 0;
		for (RosterPosition r: available.values()) sum += r.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {

		if (playerIDs.containsKey(p.getfID())) return false;
		RosterPosition r = available.get(p.getPosition());
		if (!r.addPlayer(p)) return false;

		available.put(p.getPosition(), r);
		playerIDs.put(p.getfID(), p.getfID());
		return true;
	}

	public LinkedList<Player> getPlayers() {
		LinkedList<Player> players = new LinkedList<>();
		for (RosterPosition r: available.values()) players.addAll(r.getPlayers());
		return players;
	}

	public boolean removePlayer(Player p) {
		playerIDs.remove(p.getfID());
		RosterPosition r = available.get(p.getPosition());
		if (!r.removePlayer(p)) return false;
		available.put(p.getPosition(), r);
		return true;
	}

}
