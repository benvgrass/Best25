package temp;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
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
		
		// create a new list of possible players to pick from every year
		ArrayList<ArrayList<Player>> playerYears = new ArrayList<ArrayList<Player>>(25);
		for(int i = 0; i < 25; i++) {
			playerYears.add(new ArrayList<Player>());
		}
		
		//add top 25 players per year
		for(Player p: players) {
			playerYears.get(p.getSeason() - 1992).add(p);
		}
		
		ArrayList<Rostor> topRostors = new ArrayList<Rostor>(25);
		for(int i = 0; i < 25; i++) {
			Rostor r = new Rostor();
			
			ArrayList<Player> YearOfPlayers = playerYears.get(i);
			Position[] totalPositions = {Position.B1, Position.B2, Position.B3, Position.C, Position.C, Position.CF, Position.DH, Position.LF, Position.OF, Position.OF, Position.RF, Position.RP, Position.RP, Position.RP, Position.RP, Position.RP, Position.RP, Position.RP, Position.SP, Position.SP, Position.SP, Position.SP, Position.SP, Position.SS, Position.SS};
			for(Player p: YearOfPlayers) {
				if(totalPositions.length == 0)
					break;
				
				if(contains(totalPositions, p.getPosition())) {
					totalPositions = remove(totalPositions, p.getPosition());
					r.add(p);
				}
			}
			topRostors.add(r);
		}
		
		//here we will have the best rostors in topRostors
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
		return true; //TODO implement this
	}
	
	public static Position[] remove(Position[] positions, Position p) {
		return null; //TODO implement this
	}
}
