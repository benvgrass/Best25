package temp;

import java.util.LinkedList;

public class RepeatPlayer {
	public boolean multiple;
	public Position plays;
	public LinkedList<Player> GamesPlayed;
	
	public RepeatPlayer(Player p) {
		this.multiple = false;
		this.plays = p.getPosition();
		this.GamesPlayed = new LinkedList<Player>();
		this.GamesPlayed.add(p);
	}
}
