package temp;

public class Player {
	private static int count = 0;
	
	public int id;
	public Position position;
	public int season;
	public String name;
	public double WAR;
	
	public Player(String position, int season, String name, double WAR) {
		this.id = ++count;
//		this.position = position;
		this.season = season;
		this.name = name;
		this.WAR = WAR;
	}
}
