package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie{
	
	
	public final static int EXPLOSION_DAMAGE = 3;
	
	private static final int SPEED= 2;//numero de ciclos por casilla
	private static final int RESISTENCE=5;
	private static final int DAMAGE=1;
	
	
	
	public ExplosiveZombie (GameWorld game, int col, int row) {
		
		super(game, col, row);

		this.lives=ExplosiveZombie.RESISTENCE;
	}
	
	
	public ExplosiveZombie() {
	}


	public ExplosiveZombie copy(GameWorld game, int col, int row) {
		
		ExplosiveZombie z=new ExplosiveZombie(game, col, row);
		
		return z;
	} 

	




	@Override
	protected String getSymbol() {

		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION;

	}
	
	public int getSpeed() {
		
		return ExplosiveZombie.SPEED;
	}
	
	public int getDamage() {
		
		return ExplosiveZombie.DAMAGE;
	}
	
	public int getEndurance() {
		
		return ExplosiveZombie.RESISTENCE;
	}


	
	@Override
	public void delayedAction() 
	{
		ExplosionAction explosionAction = new ExplosionAction(col,row,EXPLOSION_DAMAGE,false);
		game.pushAction(explosionAction);
		
	}

}
