package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie {
	
	
	
	private static final int SPEED= 4;//numero de ciclos por casilla
	private static final int RESISTENCE=2;
	private static final int DAMAGE=1;
	
	
	
	public Sporty (GameWorld game, int col, int row) {
		
		super(game, col, row);

		this.lives=Sporty.RESISTENCE;
	}
	
	
	public Sporty() {
	}

	public Sporty copy(GameWorld game, int col, int row) {
		
		Sporty z=new Sporty(game, col, row);
		
		return z;
	} 

	



	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected String getSymbol() {

		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION;

	}
	
	public int getSpeed() {
		
		return Sporty.SPEED;
	}
	
	public int getDamage() {
		
		return Sporty.DAMAGE;
	}
	
	public int getEndurance() {
		
		return Sporty.RESISTENCE;
	}

	/*
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}*/


	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
}
