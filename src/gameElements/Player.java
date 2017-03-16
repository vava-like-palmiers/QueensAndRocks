package gameElements;

public class Player {
	private int number;
	private String colorMode;
	
	public Player (int num){
		number = num;
		colorMode = "bw";
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getColorMode(){
		return colorMode;
	}
	
	public void setColorMode(String color){
		colorMode = color;
	}
	
	public String toString(){
		return number + " " + colorMode + "\n";
	}
}
