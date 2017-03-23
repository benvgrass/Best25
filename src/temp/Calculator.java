package temp;

import java.io.*;
import java.util.*;


public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	//all players info vars
	//status vars
	private static Roster currentRoster = new Roster();
//
//	private static final double allowThreshold = 1.1; //take players with war greater than this
//	private static final int printThreshold = 12; //print if in a loop greater than this number
//	private static final int timing  = 22; //iteration we are trying to time, must be greater than printThreshold
//	private static long mili;
	
	public static void main(String[] args) throws IOException {
		ArrayList<Player> players = getPlayersFromFile();
		players.sort(Comparator.comparing(Player::getWAR));
		System.out.println(players.size());
		

	}
	
	public static List<Player> compute(int startYear) {
		// TODO: implement calculator
		return null;
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
				 System.err.println("info was of length" + info.length + "at player " + players.size());
				 System.exit(-1);
			 }
			 p = new Player(info[0], Integer.parseInt(info[1]), info[2], Double.parseDouble(info[3]), Integer.parseInt(info[4]));
			 players.add(p);
		}
		scanner.close();
		
		return players;
	}
}
