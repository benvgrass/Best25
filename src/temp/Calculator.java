package temp;

import java.io.*;
import java.util.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;


public class Calculator {	
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	private static ArrayList<ArrayList<Player>> years;
	private static double[] bestPerYear;
	private static LinkedList<Player> bestPlayers;
	private static double bestWar = 0;
	private static double currMax = 0;
	private static Roster currentRoster = new Roster();
	private static HashMap<Set<Position>, Double> bestYearPosWAR[];

	@SuppressWarnings("unchecked")
	public static void initBestYearPosWAR() {
		bestYearPosWAR = (HashMap<Set<Position>, Double>[]) new HashMap[25];
		for (int i = 0; i < bestYearPosWAR.length; i++) {
			bestYearPosWAR[i] = new HashMap<>();
		}
	}
	public static void main(String[] args) throws IOException {
		initBestYearPosWAR();
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
		
		years = new ArrayList<ArrayList<Player>>(25);
		for(int i = 0; i < 25; i++) {
			years.add(new ArrayList<Player>());
		}
		int count = 0;
		for(Player p: players) {
			if(p.getWAR() > 1.1) {
				years.get(p.getSeason() - 1992).add(p);
				count++;
			}
		}
		System.out.println(count);
//		if (count > 0) return;
		bestPerYear = new double[25];
		for(int i = 0; i < 25; i++) {
			bestPerYear[i] = years.get(i).get(0).getWAR();
		}
		for(int i = 1; i < 25; i++) {
			bestPerYear[i] += bestPerYear[i - 1]; 
		}
		
		System.out.println(Arrays.toString(bestPerYear));
		findBest(24);
		
		System.out.println("best WAR: " + bestWar);
		System.out.println(bestPlayers.toString());
	}

	private static double maxRemainingWAR(int year, Set<Position> availablePositions) {
		double remaining = 0;
		for (;year >= 0; year--) {
			if (!bestYearPosWAR[year].containsKey(availablePositions)){
				double yearMax = 0;
				for (Player player: years.get(year)) {
					double war = player.getWAR();
					if (war > yearMax && availablePositions.contains(player.getPosition())) yearMax = war;
				}
				bestYearPosWAR[year].put(availablePositions, yearMax);
			}
			remaining += bestYearPosWAR[year].get(availablePositions);
		}
		return remaining;
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

	@SuppressWarnings("unchecked")
	private static void findBest(int year) {
		if(year < 0) {
			return;
		}
		
		int i = 0;
		double currWar = currentRoster.getWar();

		for(Player p: years.get(year)) {
			if(year > 16) {
				System.out.println(year + ": " + (++i) + ", currWar: " + currWar + ", currMax: " + currMax +
						", bestWar: " + bestWar);
			}

			Set<Position> remainingPositions = currentRoster.availablePositions();
			if (currentRoster.remainingSpots(p.getPosition()) <= 1) {
				remainingPositions.remove(p.getPosition());
			}
			if(year > 0 && currWar + maxRemainingWAR(year - 1,remainingPositions) + p.getWAR() < bestWar) {
				if (year > 15) System.out.println("Sorry Babe... " + year);
				return;
			}
			
			if(currentRoster.add(p)) { //adding player is valid
				double nextWar = currentRoster.getWar();
				if(year == 0) {
					if(nextWar > bestWar) {
						bestWar = nextWar;
						bestPlayers = currentRoster.getPlayers();
						System.out.println("New Best: " + bestWar);
						System.out.println(bestPlayers);
					}
				} else {
					findBest(year - 1);
				}
				currentRoster.removePlayer(p);
			}
		}
	}
}
