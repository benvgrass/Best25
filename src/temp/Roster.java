package temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Roster {
	
	List<Player> players;
	HashMap<Position, Integer> available;

	public Roster () {
		players = new ArrayList<>();
		available = new HashMap<>();
		available.put(Position.C, 2);
		available.put(Position.B1, 1);
		available.put(Position.B2, 1);
		available.put(Position.B3, 1);
		available.put(Position.SS, 2);
		available.put(Position.LF, 1);
		available.put(Position.CF, 1);
		available.put(Position.RF, 1);
		available.put(Position.OF, 2);
		available.put(Position.SP, 5);
		available.put(Position.RP, 7);
		available.put(Position.DH, 1);
	}
	
	public double getWar() {
		int sum = 0;
		for (Player p: players) sum += p.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {
		int availableNumber;
		if ((availableNumber = available.get(p.getPosition())) > 0) {
			players.add(p);
			available.replace(p.getPosition(), availableNumber - 1);
		}

		return false;
	}

}
