package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant{

	private static final int COST=50;
	private static final int ENDURANCE=3;
	private static final int DAMAGE=1;	
	
	
	public Peashooter(GameWorld game,int col,int row) {
		
		super(col, row, ENDURANCE, game);
		this.cooldownCycles=0;


	}

	
	public Peashooter() {
		super(-1, -1, ENDURANCE, null);

	}


	@Override
	public String getName() {
		

		return Messages.PEASHOOTER_NAME;
	}


	@Override
	public int getCost() {

		return Peashooter.COST;
	}

	@Override
	protected String getSymbol() {
		
		return "p";
	}

	@Override
	public String getDescription() {
		
		return Messages.PLANT_DESCRIPTION;
		//(Messages.SUNFLOWER_NAME_SHORTCUT,Sunflower.COST, Sunflower.DAMAGE, Sunflower.ENDURANCE);;
	}

	@Override
	public void update() {
		
		if(this.isAlive() ) {
		//&& !game.allZombiesDied()
			
			int i=this.col;
			while (i<GameWorld.NUM_COLS && !game.attackZombie(i, this.row, Peashooter.DAMAGE)) {
				
				i++;
			}
			
		}
		
		
	}
	
	
	@Override
	public Plant copy(GameWorld game, int col, int row) {
		Peashooter p=new Peashooter(game, col, row);
		
		
		return p;
	}


	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}


	
	
	
}