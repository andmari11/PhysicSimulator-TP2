package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut  extends Plant {
	
	

	private static final int COST=50;
	private static final int ENDURANCE=3;
	private static final int DAMAGE=1;	


	public WallNut(int col, int row, int lives, GameWorld game) {
		super(col, row, lives, game);
		// TODO Auto-generated constructor stub
	}
	
	public WallNut() {
		super(-1, -1, ENDURANCE, null);
	}
	


	@Override
	public boolean catchObject() {
		return false;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return WallNut.COST;
	}
	@Override
	public Plant copy(GameWorld game, int col, int row) {
		WallNut p=new WallNut( col, row, this.lives,game);
		
		
		return p;
	}



	@Override
	protected String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
	
   return Messages.PLANT_DESCRIPTION.formatted(Messages.WALL_NUT_NAME,WallNut.COST, WallNut.DAMAGE, WallNut.ENDURANCE);
	}

	@Override
	public void update() {
		
	}

}
