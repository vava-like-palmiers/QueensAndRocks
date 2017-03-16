package gameElements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.NoSuchElementException;

import eval.Eval;

public class Board {

	// ATTENTION
	// Ceci est un squelette incomplet contenant uniquement le profil de
	// quelques m√©thodes, dans le but de compiler la classe GameUI sans erreurs
	// Il manque les getters et les setters ainsi que les classes externes
	// telles que Square, Eval, Game, Player,...

	private Game game;
	private int size;
	private int numberOfPieces;
	private Square[][] board;

	public Board(int s) {
		game = Game.getInstance();
		size = s;
		board = new Square[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new Empty(game.getPlayer0());
			}
		}
	}

	public Board(Game g, int s, int nb, Square[][] b) {
		game = g;
		size = s;
		numberOfPieces = nb;
		board = b.clone();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public Square[][] getBoard() {
		return board;
	}

	public void setBoard(Square[][] board) {
		this.board = board;
	}

	// ---------------TP1------------------------
	public Square getPiece(int i, int j) {
		return board[i][j];
	}

	public void setPiece(int i, int j, Square piece) {
		board[i][j] = piece;
		numberOfPieces++;
	}

	public boolean estReine(int i, int j) {
		return board[i][j] instanceof Queen;
	}

	public boolean isAccessible(int h, int w) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (estReine(i, j)) {
					// si false = non accessible, retourne true si accessible
					if (!parcoursColReine(j, w) || !parcoursLigReine(i, h) || !parcoursDiagReine(i, j, h, w)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// teste colonne de la reine dÈtectÈe et celle de la case
	public boolean parcoursColReine(int jR, int jC) {
		return !(jR == jC);

	}

	// teste la ligne de la reine dÈtectÈe et de la case
	public boolean parcoursLigReine(int iR, int iC) {
		return !(iR == iC);
	}

	// teste les diagonales de la reine dÈtectÈe
	public boolean parcoursDiagReine(int iR, int jR, int iC, int jC) {
		// si case ‡ droite de reine
		if (jC > jR) {
			if (!aDroiteDeReine(iR, jR, iC, jC))
				return false;
		} else {
			if (!aGaucheDeReine(iR, jR, iC, jC))
				return false;
		}
		return true;

	}

	public boolean aDroiteDeReine(int iR, int jR, int iC, int jC) {
		int distance;
		// teste pour diagonale vers bas
		distance = jC - jR;
		if (iR + distance < size && jR + distance < size) {
			if (posIdentiques(iR + distance, jR + distance, iC, jC)) {
				return false;
			}
		}
		// teste pour diagonale vers haut
		distance = jC - jR;
		if (iR - distance >= 0 && jR + distance < size) {
			if (posIdentiques(iR - distance, jR + distance, iC, jC)) {
				return false;
			}
		}
		return true;
	}

	public boolean aGaucheDeReine(int iR, int jR, int iC, int jC) {
		int distance;
		// teste pour diagonale vers bas
		distance = jC - jR;
		if (iR + distance < size && jR + distance < size) {
			if (posIdentiques(iR + distance, jR + distance, iC, jC)) {
				return false;
			}
		}
		// teste pour diagonale vers haut

		distance = jC - jR;
		if (iR - distance >= 0 && jR + distance < size) {
			if (posIdentiques(iR - distance, jR + distance, iC, jC)) {
				return false;
			}
		}
		return true;
	}

	public boolean posIdentiques(int iR, int jR, int iC, int jC) {
		return iR == iC && jR == jC;
	}

	public boolean isEmpty(int i, int j) {
		return board[i][j] instanceof Empty;
	}

	public void removePiece(int i, int j) {
		board[i][j] = new Empty(game.getPlayer0());
		numberOfPieces--;
	}

	public Board clone() {
		Square[][] s = new Square[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				s[i][j] = board[i][j];
			}
		}
		return new Board(game, size, numberOfPieces, s);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				s.append(board[i][j].toString() + "\t");
			}
			s.append("\n\n");
		}
		return s.toString();
	}

	public int numberOfAccessible() {
		int cpt = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (isAccessible(i, j))
					cpt++;
			}
		}
		return cpt;
	}

	public int numberOfQueens() {
		int cpt = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (estReine(i, j))
					cpt++;
			}
		}
		return cpt;
	}

	public boolean placeQueen(int i, int j) {
		if (isAccessible(i, j)) {
			setPiece(i, j, new Queen(game.getPlayer0()));
			return true;
		}
		return false;
	}

	// ----------TP2-----------------------
	
	public boolean isSolution() {
		int nbReines = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (estReine(i, j)) {
					nbReines++;
				}
			}
		}
		return nbReines == size;
	}
	
	public ArrayList<Board> getSuccessors() {
		ArrayList<Board> succ = new ArrayList<Board>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (isAccessible(i, j)) {
					Board b = this.clone();
					b.setPiece(i, j, new Queen(new Player(0)));
					succ.add(b);
				}
			}
		}
		return succ;
	}
	
	public ArrayList<Board> depthFirstSearch(Board initialState) throws Exception {
		ArrayList<Board> sol = new ArrayList<Board>();
		if (initialState.isSolution()) {
			sol.add(initialState);
			return sol;
		}
		for (Board b : initialState.getSuccessors()) {
			try {
				sol = depthFirstSearch(b);
				sol.add(initialState);
				return sol;
			} catch (NoSuchElementException e) {
			}
		}
		throw new NoSuchElementException("Pas de solutions");

	}
	
	public ArrayList<Board> depthFirstSearch() throws Exception {
		ArrayList<Board> sol = new ArrayList<Board>();
		Board initialState = new Board(size);
		if (initialState.isSolution()) {
			sol.add(initialState);
			return sol;
		}
		for (Board b : initialState.getSuccessors()) {
			try {
				sol = depthFirstSearch(b);
				sol.add(initialState);
				return sol;
			} catch (NoSuchElementException e) {
			}
		}
		throw new NoSuchElementException("Pas de solutions");

	}

	public void solutionSteps(Board b) throws Exception {
		ArrayList<Board> list = b.depthFirstSearch(b);
		Collections.reverse(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Etape : " + (i + 1));
			System.out.println(list.get(i).toString());
		}
	}

	public void testTemps1(Board b) throws Exception {
		long begin = System.currentTimeMillis();
		System.out.println("DÈbut...");
		depthFirstSearch(b);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		System.out.println("Temps d'Èxecution depthFirstSearch : " + time);
	}
	
	public ArrayList<Board> getNewSuccessors() {
		ArrayList<Board> succ = new ArrayList<Board>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (isAccessible(j, i)) {
					Board b = this.clone();
					b.setPiece(j, i, new Queen(new Player(0)));
					succ.add(b);
				}
			}
		}
		return succ;
	}

	public ArrayList<Board> depthFirstSearch2(Board initialState) throws Exception {
		ArrayList<Board> sol = new ArrayList<Board>();
		if (initialState.isSolution()) {
			sol.add(initialState);
			return sol;
		}
		for (Board b : initialState.getNewSuccessors()) {
			try {
				sol = depthFirstSearch2(b);
				sol.add(initialState);
				return sol;
			} catch (NoSuchElementException e) {
			}
		}
		throw new NoSuchElementException("Pas de solutions");

	}

	public void solutionSteps2(Board b) throws Exception {
		ArrayList<Board> list = b.depthFirstSearch2(b);
		Collections.reverse(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Etape : " + (i + 1));
			System.out.println(list.get(i).toString());
		}
	}
	
	public void testTemps2(Board b) throws Exception {
		long begin = System.currentTimeMillis();
		System.out.println("DÈbut...");
		depthFirstSearch2(b);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		System.out.println("Temps d'Èxecution depthFirstSearch2 : " + time);
	}

	public int[] boardToArray(Board b) {
		int[] tab = new int[size];
		boolean reine = false;
		for (int i = 0; i < b.getSize(); i++) {
			for (int j = 0; j < b.getSize(); j++) {
				if (estReine(j, i)) {
					tab[i] = j;
					reine = true;
				}
			}
			if (!reine)
				tab[i] = -1;
			reine = false;
		}
		return tab;
	}

	public Board arrayToBoard(int[] tab) {
		Board board = new Board(tab.length);
		for (int i = 0; i < board.getSize(); i++) {
			if (tab[i] != -1)
				board.setPiece(tab[i], i, new Queen(new Player(0)));
		}
		return board;
	}

	public ArrayList<int[]> getArraySuccessors(int[] tab) {
		ArrayList<int[]> succ = new ArrayList<int[]>();
		for (int i = 0; i < tab.length; i++) {
			if (tab[i] == -1) {
				for (int j = 0; j < tab.length; j++) {
					if (this.arrayToBoard(tab).isAccessible(j, i)) {
						int[] tabSucc = arrayClone(tab);
						tabSucc[i] = j;
						succ.add(tabSucc);
					}
				}
			}
		}
		return succ;
	}

	public int[] arrayClone(int[] tab) {
		int[] newTab = new int[tab.length];
		for (int i = 0; i < tab.length; i++) {
			newTab[i] = tab[i];
		}
		return newTab;
	}

	public boolean isSolutionArray(int[] tab) {
		for (int i : tab) {
			if (i == -1)
				return false;
		}
		return true;
	}

	public ArrayList<int[]> depthFirstSearchArray(int[] initialState) throws Exception {
		ArrayList<int[]> sol = new ArrayList<int[]>();

		if (isSolutionArray(initialState)) {
			sol.add(initialState);
			return sol;
		}
		for (int[] tab : getArraySuccessors(initialState)) {
			try {
				sol = depthFirstSearchArray(tab);
				sol.add(initialState);
				return sol;
			} catch (NoSuchElementException e) {
			}
		}
		throw new NoSuchElementException("Pas de solution");
	}

	public ArrayList<int[]> depthFirstSearchArray() throws Exception {
		ArrayList<int[]> sol = new ArrayList<int[]>();
		int[] initialState = new int[size];
		if (isSolutionArray(initialState)) {
			sol.add(initialState);
			return sol;
		}
		for (int[] tab : getArraySuccessors(initialState)) {
			try {
				sol = depthFirstSearchArray(tab);
				sol.add(initialState);
				return sol;
			} catch (NoSuchElementException e) {
			}
		}
		throw new NoSuchElementException("Pas de solution");
	}

	public void solutionStepsArray(int[] tab) throws Exception {
		ArrayList<int[]> list = depthFirstSearchArray(tab);
		Collections.reverse(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Etape : " + (i + 1));
			System.out.println(this.arrayToBoard(list.get(i)).toString());
		}
	}
	
	public void testTempsArray(int[] tab) throws Exception {
		long begin = System.currentTimeMillis();
		System.out.println("DÈbut...");
		depthFirstSearchArray(tab);
		long end = System.currentTimeMillis();
		float time = ((float) (end-begin)) / 1000f;
		System.out.println("Temps d'Èxecution depthFirstSearchArray : " + time);
	}

	// ------------TP3----------------------
	public boolean isAccessible2(int i, int j, Player currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean placeQueen2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean placeRock2(int i, int j, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getNumberOfRocksLeft(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getScore(Player player) {
		// TODO Auto-generated method stub
		return 0;
	}

	// ----------------------TP4&5--------------------------
	public boolean isFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	public Board minimax(Board b, Player currentPlayer, int minimaxDepth, Eval evaluation) {
		// TODO Auto-generated method stub
		return null;
	}

}
