package temp;

import java.io.*;
import java.util.ArrayList;

public class Calculator {
	private static final String dataPath = "data" + File.separator + "jays.csv";
	
	public static void main(String[] args) throws IOException {
		ArrayList<Player> players = getPlayersFromFile();
		System.out.println(players.size());
		
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
}
