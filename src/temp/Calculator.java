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
        System.out.println("Number of players: " + players.size());

        Roster result = compute(1992, players);
        System.out.println(result);

	}
	
	public static Roster compute(int startYear, ArrayList<Player> players) {
        players.sort(Comparator.comparing(Player::getWAR).reversed());
        Roster roster = new Roster(startYear);
	    PriorityQueue<PlayerPair> playerPairs = getPlayerPairs(players);

        int c = 0;
        while (!roster.isValidRoster() && !playerPairs.isEmpty()) {
            c++;
            roster.addPlayerPair(playerPairs.poll());
        }

        System.out.println(c);
        assert roster.isValidRoster();

		return roster;
	}

    /**
     * Returns Priority Queue of player pairs, priority is highest first
     * @param players list of players
     * @return priority queue of player pairs
     */
	private static PriorityQueue<PlayerPair> getPlayerPairs(ArrayList<Player> players) {
	    int length = players.size();
	    int initialCapacity = (length*(length - 1))/2;
	    PriorityQueue<PlayerPair> playerPairs = new PriorityQueue<>(initialCapacity, PlayerPair.comparator);

	    for (int i = 0; i < length; i++) {
	        for (int j = i + 1; j < length; j++) {
	            if (Roster.isValidPair(players.get(i), players.get(j))) {
	                PlayerPair newPair = new PlayerPair(players.get(i), players.get(j));
	                playerPairs.add(newPair);
                }
            }
        }
	    return playerPairs;
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
