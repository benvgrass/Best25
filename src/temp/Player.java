package temp;

public class Player {
	private static int count = 0;
	
	protected static final Player defaultPlayer = new Player();
	
	private int id;
	private Position position;
	private int season;
	private String name;
	private double WAR;
	private int fID;
	
	public Player(String position, int season, String name, double WAR,
				  int fID) {
		this.id = ++count;
		this.position = parsePosition(position);
		this.season = season;
		this.name = name;
		this.WAR = WAR;
		this.fID = fID;
	}
	
	public Player() {
		this.id = count;
		this.position = null;
		this.season = 0;
		this.name = null;
		this.WAR = 0;
		this.fID = 0;
	}

	public int getId() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	public int posInt() {
	    return position.ordinal();
    }

	public int getSeason() {
		return season;
	}

	public String getName() {
		return name;
	}

	public double getWAR() {
		return WAR;
	}

	public int getfID() {
		return fID;
	}

	private static Position parsePosition(String posString) {
		switch (posString){
			case "C":
				return Position.C;
			case "B1":
				return Position.B1;
			case "B2":
				return Position.B2;
			case "B3":
				return Position.B3;
			case "SS":
				return Position.SS;
			case "LF":
				return Position.LF;
			case "CF":
				return Position.CF;
			case "RF":
				return Position.RF;
			case "OF":
				return Position.OF;
			case "DH":
				return Position.DH;
			case "SP":
				return Position.SP;
			case "RP":
				return Position.RP;
			default:
				System.err.println("Parse error: got default val");
				return Position.RP;
		}
	}

	@Override
	public String toString() {
		return season + "\t" + position + "\t" + name + "\tWAR: " + WAR;
	}
}
