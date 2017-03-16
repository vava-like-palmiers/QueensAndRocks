package eval;

import gameElements.Board;
import gameElements.Player;

public class Eval0 implements Eval{

	@Override
	public float getEval(Player player, Board b) {
		return 0;
	}

}
