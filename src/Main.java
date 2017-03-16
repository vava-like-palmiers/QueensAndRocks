import java.util.ArrayList;
import java.util.Scanner;

import gameElements.Board;
import gameElements.Player;
import gameElements.Queen;
import graphics.GameUI;

public class Main {
	public Main() throws Exception {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir une taille :");
		String str = sc.nextLine();
		
		Board b = new Board(Integer.parseInt(str));
		
		GameUI g = new GameUI(b, 1); g.launch();
		/*
		b.setPiece(0, 0, new Queen(new Player(0)));
		System.out.println("Test depthFirstSearch\n");
		b.solutionSteps(b);
		b.testTemps1(b);
		System.out.println("Test depthFirstSearch2\n");
		b.solutionSteps2(b);
		b.testTemps2(b);
		System.out.println("Test depthFirstSearchArray\n");
		b.solutionStepsArray(b.boardToArray(b));
		b.testTempsArray(b.boardToArray(b));*/
		
		}

	public static void main(String[] a) throws Exception {
		try{
		new Main();
		}catch(Exception e){
			System.out.println("Pas de solution...");
		}
	}
}
