package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Plant extends GameObject{

	Plant(int col, int row, int lives, GameWorld game){
		
		super(game, col, row);
		
		this.lives=lives;
	}
	
	
	

	
	
	public abstract String getName();

	public abstract int getCost();
	

	
	@Override
	public boolean receiveZombieAttack(int damage) {
		
		this.receiveDamage(damage);
		return true;
	}

	
	@Override
	public boolean receivePlantAttack(int damage) {
		
		return false;
	}
	
	@Override
	public boolean isNpc() {
		// ????
		return true;
	}
	
	abstract public Plant copy(GameWorld game, int col, int row);
	
	
	
	@Override
	public boolean isAlive() {
		return this.lives>0;
	}
	
	

	@Override
	public void onEnter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExit() {
		
		
	}

}
