package gameElements;

import javax.swing.ImageIcon;

public class Queen implements Square{

	private Player player;
	private ImageIcon symbole;
	
	public Queen(Player p){
		player = p;
	}
	
	public ImageIcon getSymbole(){
		return symbole;
	}
	
	public void setSymbole(ImageIcon img){
		symbole = img;
	}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public String toString(){
		if(player.getNumber() == 0)
			return "Q0";
		return "Q1";
	}

}
