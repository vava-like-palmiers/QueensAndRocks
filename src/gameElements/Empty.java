package gameElements;

import javax.swing.ImageIcon;

public class Empty implements Square{
	private Player player;
	private ImageIcon symbole;

	public Empty(Player p){
		player = p;
	}
	
	public ImageIcon getSymbole() {
		return symbole;
	}

	public void setSymbole(ImageIcon img) {
		symbole = img;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public String toString() {
		return "-";
	}
}
