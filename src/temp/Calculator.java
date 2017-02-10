package temp;

import java.io.*;
import java.util.*;


public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	//all players info vars
	private static ArrayList<ArrayList<ArrayList<Player>>> array;
	private static int[] positionsMappings = {0, 1, 2, 3, 4, 0, 1, 2, 3, 4, 5, 6, 0, 0, 0, 1, -1, -1, -1, -1, -1, -1, 1, 1, -1}; //-1 means there is only one
																																 // number represents the occurrence of the repeat
																																 // for more info refer to positions[] in main
	private static double[] bestPerYear;
	
	//result vars
	private static Collection<Player> bestPlayers;
	private static double bestWar = 0;
	
	//status vars
	private static Roster currentRoster = new Roster();
	private static boolean[] taken = new boolean[25];
	
	private static final double allowThreshold = 2.5; //take players with war greater than this
	private static final int printThreshold = 21; //print if in a loop greater than this number
	private static final int timing  = 22; //iteration we are trying to time, must be greater than printThreshold
	private static long mili;
	
	public static void main(String[] args) throws IOException {
		ArrayList<Player> players = getPlayersFromFile();
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
		
		//split it by year
		ArrayList<ArrayList<Player>> years = new ArrayList<ArrayList<Player>>(25);
		for(int i = 0; i < 25; i++) {
			years.add(new ArrayList<Player>());
		}
		for(Player p: players) {
			if(p.getWAR() > allowThreshold) {
				years.get(p.getSeason() - 1992).add(p);
			}
		}
		
		//set up array to know when to call it quits
		bestPerYear = new double[25];
		bestPerYear[0] = years.get(0).get(0).getWAR();
		for(int i = 1; i < 25; i++) {
			bestPerYear[i] = years.get(i).get(0).getWAR() + bestPerYear[i - 1]; 
		}
		
		//set up 2-D map of year - position
		array = new ArrayList<ArrayList<ArrayList<Player>>>(25);
		for(int i = 0; i < 25; i++) {
			array.add( new ArrayList<ArrayList<Player>>(25) );
			for(int j = 0; j < 25; j++) {
				array.get(i).add( new ArrayList<Player>() );
			}
		}
		
		Position[] positions = {Position.SP, Position.SP, Position.SP, Position.SP, Position.SP, 
								Position.RP, Position.RP, Position.RP, Position.RP, Position.RP, Position.RP, Position.RP,
								Position.C, Position.SS, Position.OF, Position.OF,
								Position.B1, Position.B2, Position.B3, Position.CF, Position.LF, Position.RF, Position.C, Position.SS, 
								Position.DH};
		//populate 2-D array of year - position
		for(int i = 0; i < 25; i++) {
			ArrayList<Player> year = years.get(i);
			for(Player p: year) {
				for(int j = 0; j < 25; j++) {
					if(positions[j] == p.getPosition())
						array.get(i).get(j).add(p);
				}
			}
		}
		
		years = null; //lets the run time enviorment clean up that data structure
		
		for(int i = 0; i < 25; i++) {
			taken[i] = false;
		}
		
		mili = System.currentTimeMillis();
		compute(24);
		System.out.println(bestPlayers.toString());
	}
	
	private static void compute(int year) {
		if(year < 0)
			return;
		else if(currentRoster.getWar() + bestPerYear[year] <= bestWar) { //check if solution is feasible
			return;
		}
		
		// temporary status printing, so we know what's going on, allows ofr timing of a certian loop
		// update timing var to control this 
		if(year >= printThreshold) {
			System.out.print(year);
			if(year == timing) {
				System.out.println(": " + (System.currentTimeMillis() - mili));
				mili = System.currentTimeMillis();
			} else{
				System.out.println();
			}
		}
		
		for(int i = 0; i < 25; i++) {
			if(taken[i])
				continue;
			ArrayList<Player> position = array.get(year).get(i);
			for(Player p : position) {
				if((positionsMappings[i] == -1)? currentRoster.add(p): currentRoster.add(p, positionsMappings[i])) {
					taken[i] = true;
					if(year == 0) {
						if(currentRoster.getWar() > bestWar) {
							bestWar = currentRoster.getWar();
							bestPlayers = currentRoster.getPlayers();
System.out.println("year: " + year + " updated best team to have War: " + bestWar); //this isn't printing but that might be ok
						}
					} else {
						compute(year - 1);
					}
					taken[i] = false;
					if(!((positionsMappings[i] == -1)? currentRoster.removePlayer(p): currentRoster.removePlayer(p, positionsMappings[i]))) {System.err.println("Failed to remove Player"); System.exit(-1);}
				}
			}
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
