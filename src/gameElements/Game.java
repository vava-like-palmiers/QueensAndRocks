package gameElements;

/**
 * @author dalessan4u
 *
 */
public class Game {
	private Player player0;
	private Queen queen0;
	private Rock rock0;
	private Player player1;
	private Queen queen1;
	private Rock rock1;
	private Empty empty;
	private static Game instance = new Game();
	
	public Game(){
		player0 = new Player(0);
		queen0 = new Queen(player0);
		rock0 = new Rock(player0);
		
		player1 = new Player(1);
		queen1 = new Queen(player1);
		rock1 = new Rock(player1);
		
		empty = new Empty(player0);
	}
	
	public static Game getInstance(){
		return instance;
	}
	
	public void setColorMode(String color){
		player0.setColorMode(color);
		player1.setColorMode(color);
	}

	public Player otherPlayer(Player p){
		if(p == player0)
			return player1;
		return player0;
	}
	
	public Player getPlayer0() {
		return player0;
	}

	public Queen getQueen0() {
		return queen0;
	}

	public Rock getRock0() {
		return rock0;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Queen getQueen1() {
		return queen1;
	}

	public Rock getRock1() {
		return rock1;
	}

	public Empty getEmpty() {
		return empty;
	}
	
}
