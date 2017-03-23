package temp;

import java.util.*;

/**
 * Our Roster will serve as our 'graph' of sorts.
 * We Want our maximal spanning roster: a roster that fills all positions with maximal value.
 * Our 'edges' will be the sum of the two players WARs.
 * In reality, if a player is on the roster, the maximal edge that can be used and isn't connected,
 * is a player that's position isn't yet filled.
 */
public class Roster {
	
	private List<Player> players;
	private int[] availablePositions;
	private boolean[] availableYears;
	private Set<Integer> usedPlayers;
	private final int START_YEAR;

	public Roster (int startYear) {
		players = new LinkedList<Player>() {
            @Override
            public String toString() {
                Iterator<Player> it = iterator();
                if (! it.hasNext())
                    return "";
                StringBuilder sb = new StringBuilder();
                for (;;) {
                    Player e = it.next();
                    sb.append(e);
                    if (! it.hasNext())
                        return sb.toString();
                    sb.append('\n');
                }
            }

        };
		availablePositions = Arrays.copyOf(Position.maxPlayersForPosition, Position.maxPlayersForPosition.length);
		availableYears =  new boolean[25];
		Arrays.fill(availableYears, true);
		usedPlayers = new HashSet<>();
		START_YEAR = startYear;
	}

	public double getWAR() {
		double sum = 0;
		for (Player p: players) sum += p.getWAR();
		return sum;
	}
	
	public boolean add(Player p) {

	    if (isValidAddition(p)) {
	        usedPlayers.add(p.getfID());
	        availablePositions[p.posInt()] -= 1;
	        availableYears[p.getSeason() - START_YEAR] = false;
	        players.add(p);
	        return true;
        }

	    return false;
	}

	public List<Player> getPlayers() {
        return players;
	}

	private boolean isValidAddition(Player playerToAdd) {
	    return (!usedPlayers.contains(playerToAdd.getfID()) && availablePositions[playerToAdd.posInt()] > 0 &&
                availableYears[playerToAdd.getSeason()-START_YEAR] && players.size() < 25);
    }


    public static boolean isValidPair(Player p1, Player p2) {
	    boolean validPositions = (p1.getPosition() != p2.getPosition()) ||
                (Position.maxPlayersForPosition[p1.posInt()] >= 2);
	    return (p1.getSeason() != p2.getSeason() && p1.getfID() != p2.getfID() && validPositions);
    }

    public boolean isValidRoster() {
	    if (players.size() != 25) return false;

	    for (int available : availablePositions) {
	        if (available != 0) {
	            System.err.println("Full Roster, positions are wrong?");
	            System.err.print(this);
                return false;
            }
        }

	    for (boolean available : availableYears) {
	        if (available) {
                System.err.println("Full Roster, years available?");
                System.err.print(this);
                return false;
            }
        }

        return true;
    }

	@Override
	public String toString() {
		return "Total WAR: " + this.getWAR() + '\n' + getPlayers().toString();
	}

}
