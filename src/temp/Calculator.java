package temp;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	public static void main(String[] args) throws IOException {
		ArrayList<Player> players = getPlayersFromFile();
		System.out.println(players.size());
		players.sort(new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				if(o1.getWAR() < o2.getWAR())
					return 1;
				else if(o1.getWAR() > o2.getWAR())
					return -1;
				return 0;
			}
		});
		System.out.println(players.size());
		
		HashMap<Integer, RepeatPlayer> repeatPlayers = new HashMap<Integer, RepeatPlayer>();
		LinkedList<Player> playedMultiple = new LinkedList<Player>();
		Player p;
		
		for(int i = 0; i < players.size(); i++) {
			p = players.get(i);
			if( repeatPlayers.containsKey(p.getfID()) ) {
				RepeatPlayer rp = repeatPlayers.get(p.getfID());
				if(rp.multiple) {
					playedMultiple.add(p);
					players.remove(i);
					i--; //so that the loop doesn't mess up
				} else if(rp.plays != p.getPosition()) {
					rp.multiple = true;
					players.remove(i);
					playedMultiple.add(p);
					for(Player mutiple: rp.GamesPlayed) {
						players.remove(mutiple);
						playedMultiple.add(mutiple);
					}
					i = i - (rp.GamesPlayed.size() + 1);
				} else { //the player is playing the same position
					rp.GamesPlayed.add(p);
				}
			} else { //there is no player with the key in the list yet
				repeatPlayers.put(p.getfID(), new RepeatPlayer(p));
				
			}
		}
		p = null;
		repeatPlayers = null;
		
		System.out.println(playedMultiple.size());
		System.out.println(players.size());
		
		System.out.println(playedMultiple.size() + players.size());
		
		ArrayList<Roster> topRosters = new ArrayList<Roster>(25);
		for(int i = 0; i < 25; i++) {
			topRosters.add(new Roster());
		}
	}
	
	private static ArrayList<Player> getPlayersFromFile() throws IOException {
		File f = new File(dataPath);
		BufferedReader scanner = new BufferedReader(new FileReader(f));
		
		ArrayList<Player> players = new ArrayList<Player>();
		String[] info;
		Player p;
		
		String data = scanner.readLine();
		while((data = scanner.readLine()) != null) {
			 info = data.split(",");
			 if(info.length != 5) {
				 System.err.println("info was of lenth " + info.length + "at player " + players.size());
				 System.exit(-1);
			 }
			 p = new Player(info[0], Integer.parseInt(info[1]), info[2], Double.parseDouble(info[3]), Integer.parseInt(info[4]));
			 players.add(p);
		}
		scanner.close();
		
		return players;
	}
	
	public static boolean contains(Position[] positions, Position p) {
		for(Position p1: positions) {
			if(p1 == p) return true;
		}
		return false;
	}
	
	public static Position[] remove(Position[] positions, Position p) {
		Position[] ps = new Position[positions.length - 1];
		int found = 0;
		for(int i = 0; i < positions.length; i++) {
			if(found == 0 && positions[i] == p) {
				found = 1;
			} else {
				ps[i - found] = positions[i];
			}
		}
		return ps;
	}
}
