package temp;

import java.util.*;

public class Roster {
	
	private HashMap<Integer, Player> players;
	
	private HashMap<Position, ArrayList<Player>> positions;
	
	public Roster () {
		this.players = new HashMap<>(50);
		this.positions = new HashMap<>(10);
		
		ArrayList<Player> sp = new ArrayList<>(5);
		ArrayList<Player> rp = new ArrayList<>(7);
		ArrayList<Player> c = new ArrayList<>(2);
		ArrayList<Player> ss = new ArrayList<>(2);
		ArrayList<Player> of = new ArrayList<>(2);
		
		for(int i = 0; i < 5; i++) {
			sp.add(Player.defaultPlayer);
		}
		positions.put(Position.SP, sp);
		
		for(int i = 0; i < 7; i++) {
			rp.add(Player.defaultPlayer);
		}
		positions.put(Position.RP, rp);
		
		for(int i = 0; i < 2; i++) {
			c.add(Player.defaultPlayer);
		}
		positions.put(Position.C, c);
		
		for(int i = 0; i < 2; i++) {
			ss.add(Player.defaultPlayer);
		}
		positions.put(Position.SS, ss);
		
		for(int i = 0; i < 2; i++) {
			of.add(Player.defaultPlayer);
		}
		positions.put(Position.OF, of);
	}

	public double getWar() {
		double sum = 0;
		for (Player p: players.values()) sum += p.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {
		return players.putIfAbsent(p.getfID(), p) == null;
	}
	
	public boolean add(Player p, int i) {
		Position pos = p.getPosition();
		Player temp = null;
		ArrayList<Player> players = positions.get(pos);
		if(i <= players.size() - 2 && players.get(i + 1).getWAR() <= p.getWAR()) { 
			//player is valid to add
			temp = players.set(i, p);
			if(!add(p)) {
				players.set(i, temp);
				return false;
			} return true;
		} else if(i <= positions.get(pos).size() - 2) {
			return false; //not valid to add
		}
		
		return add(p);
	}
	
	public boolean removePlayer(Player p) {
		return players.remove(p.getfID(), p);
	}
	
	public boolean removePlayer(Player p, int i) {
		if(i <= players.size() - 2)
			positions.get(p.getPosition()).set(i, Player.defaultPlayer);
		return removePlayer(p);
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


	@Override
	public String toString() {
		return getPlayers().toString();
	}

}
