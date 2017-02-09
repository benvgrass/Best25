package temp;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Calculator {	
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	private static ArrayList<ArrayList<Player>> years;
	private static LinkedList<Player> bestPlayers;
	private static int bestWar = 0;
	
	private static Roster currentRoster = new Roster();
	
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
		
		years = new ArrayList<ArrayList<Player>>(25);
		for(int i = 0; i < 25; i++) {
			years.add(new ArrayList<Player>());
		}
		
		for(Player p: players) {
			if(p.getWAR() > 0) {
				years.get(p.getSeason() - 1992).add(p);
			}
		}
		
		findBest(24);
		
		System.out.println("best WAR: " + bestWar);
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

	private static int i = 0;
	private static void findBest(int year) {
		if(year < 0) {
			return;
		}
		
		for(Player p: years.get(year)) {
			if(year == 24) {
				System.out.println(year + ": " + (++i));
			}
			if(currentRoster.add(p)) { //adding player is valid
				if(year == 0) {
					if(currentRoster.getWar() > bestWar) {
						bestPlayers = currentRoster.getPlayers();
					}
				} else {
					findBest(year - 1);
				}
				currentRoster.remove(p);
			}
		}
	}
}
