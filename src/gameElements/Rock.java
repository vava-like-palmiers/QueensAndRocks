package gameElements;

import javax.swing.ImageIcon;

public class Rock implements Square {

	private Player player;
	private ImageIcon symbole;

	public Rock(Player p){
		player = p;
	}
	
	public ImageIcon getSymbole() {
		return symbole;
	}

	public void setSymbole(ImageIcon img) {
		symbole = img;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(Player p) {
		player = p;
	}

	public String toString() {
		if(player.getNumber() == 0)
			return "R0";
		return "R1";
	}
}
