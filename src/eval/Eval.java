package eval;

import gameElements.Board;
import gameElements.Player;

public interface Eval {
	public float getEval(Player player, Board b);
}
