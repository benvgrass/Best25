package temp;

import java.io.*;
import java.util.*;


public class Calculator {

	private static final String dataPath = "data" + File.separator + "jays.csv";

//	private static final double allowThreshold = 1.1; //take players with war greater than this
//	private static final int printThreshold = 12; //print if in a loop greater than this number
//	private static final int timing  = 22; //iteration we are trying to time, must be greater than printThreshold
//	private static long mili;
	
	public static void main(String[] args) throws IOException {

		ArrayList<Player> players = getPlayersFromFile();
		players.sort(Comparator.comparing(Player::getWAR).reversed());
        System.out.println("Number of players: " + players.size());

        Roster result = compute(1992, players);

	}
	
	public static Roster compute(int startYear, ArrayList<Player> players) {
	    Roster roster = new Roster(startYear);
	    Player[] initialPlayers = maxPair(players);
	    System.out.println(initialPlayers[0].toString() + '\n' + initialPlayers[1].toString());

        assert initialPlayers.length == 2;

	    boolean v1 = roster.add(initialPlayers[0]);
	    boolean v2 = roster.add(initialPlayers[1]);
	    assert v1 && v2;

		// TODO: implement kruskals
		return roster;
	}

    /**
     * Get maximal player pair
     * @param players list of players
     * @return maximal war pair of two players who can be on roster together
     */
	private static Player[] maxPair(ArrayList<Player> players) {
	    int length = players.size();
	    double max = 0;
	    Player[] maxPlayerPair = new Player[2];

	    for (int i = 0; i < length; i++) {
	        if (players.get(i).getWAR() + players.get(0).getWAR() < max) break;
	        for (int j = 0; j < length; j++) {
	            if (players.get(i).getWAR() + players.get(j).getWAR() < max) break;
	            if (Roster.isValidPair(players.get(i), players.get(j)) &&
                        players.get(i).getWAR() + players.get(j).getWAR() > max) {
	                maxPlayerPair[0] = players.get(i);
	                maxPlayerPair[1] = players.get(j);
	                max = players.get(i).getWAR() + players.get(j).getWAR();
                }
            }
        }
	    return maxPlayerPair;
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
