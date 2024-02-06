package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant{

	private static final int COST=20;
	private static final int ENDURANCE=1;
	private static final int FREQUENCY=10;
	private static final int COOLDOWNCYCLES=3;
	
	public Sunflower(GameWorld game,int col,int row) {
		
		super(col, row, ENDURANCE, game);

		this.cooldownCycles=0;
	}
	

	
	
	public Sunflower() {
		super(-1, -1, ENDURANCE, null);
	}




	@Override
	public String getName() {

		return Messages.SUNFLOWER_NAME;
	}


	@Override
	public int getCost() {

		return Sunflower.COST;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getDescription() {

		
		return Messages.PLANT_DESCRIPTION;
		//(Messages.SUNFLOWER_NAME_SHORTCUT,Sunflower.COST, Sunflower.DAMAGE, Sunflower.ENDURANCE);
	}			


	@Override
	public void update() {	
		
		if(this.isAlive()) {
			
			if(this.cooldownCycles>=COOLDOWNCYCLES) {
				
				game.addSunCoins(10);
				this.cooldownCycles=1;
			}
			else {
				
				this.cooldownCycles++;
			}
		}
	}
	
	@Override
	public Plant copy(GameWorld game, int col, int row) {
		Sunflower p=new Sunflower(game, col, row);
		
		
		
		return p;
	}





	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}





	
	
}