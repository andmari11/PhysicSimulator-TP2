package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant {
	

	private static final int COST=50;
	private static final int ENDURANCE=3;
	private static final int DAMAGE=1;	

	public CherryBomb(int col, int row, int lives, GameWorld game) {
		super(col, row, lives, game);
	}
	
	
	public CherryBomb() {
		super(-1, -1, ENDURANCE, null);
	}





	@Override
	public boolean catchObject() {
		return false;
	}


	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}


	@Override
	public int getCost() {
		return COST;
	}


	@Override
	public Plant copy(GameWorld game, int col, int row) {
		CherryBomb p= new CherryBomb(col, row, ENDURANCE, game);
		
		return p;
	}



	@Override
	protected String getSymbol() {
		return Messages.CHERRY_BOMB_SYMBOL;
	}


	@Override
	public String getDescription() {
		return Messages.PLANT_DESCRIPTION.formatted(getName(), COST,DAMAGE,ENDURANCE);
	}

	@Override
	public void delayedAction() 
	{
		ExplosionAction explosionAction = new ExplosionAction(col,row,DAMAGE,true);
		game.pushAction(explosionAction);
	}


	@Override
	public void update() {
		
	}
}
