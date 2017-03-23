package temp;


public enum Position {
	SP, C, B1, B2, B3, SS, LF, CF, RF, RP, OF, DH;

    // maxPlayerForPosition[Position] is the max number of players that can be at that position
    public static final int[] maxPlayersForPosition =  {5,2,1,1,1,2,1,1,1,7,2,1};
//    public static final int[] maxPlayersForPosition =  {5,2,2,2,1,2,1,1,1,6,1,1};

}
