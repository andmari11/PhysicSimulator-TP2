package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject{

	
	private static final int SPEED= 2;//numero de ciclos por casilla
	private static final int RESISTENCE=5;
	private static final int DAMAGE=1;
	
	
	
	public Zombie(GameWorld game, int col, int row) {
		
		super(game, col, row);
		this.lives=Zombie.RESISTENCE;
	}
	
	
	public Zombie() {
	}


	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		
		this.receiveDamage(damage);
		
		return true;
	}
	
	public Zombie copy(GameWorld game, int col, int row) {
		
		Zombie z=new Zombie(game, col, row);
		
		return z;
	}

	@Override
	public boolean isNpc() {

		return true;
	}

	@Override
	protected String getSymbol() {


		return Messages.ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION;

	}

	@Override
	public void update() {
		
		if(this.isAlive()) {
			
			if(!game.attackPlant(this.col-1, this.row, Zombie.DAMAGE)) {
				
				
				if(this.cooldownCycles>=Zombie.SPEED ) {
					//&& game.isPositionEmpty(col-1, row)
					this.col--;
					this.cooldownCycles=0;
				}
				
				this.cooldownCycles++;
			}

		}		
	}
	
	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		delayedAction();
	}
	
	public int getSpeed() {
		
		return Zombie.SPEED;
	}
	
	public int getDamage() {
		
		return Zombie.DAMAGE;
	}
	
	public int getEndurance() {
		
		return Zombie.RESISTENCE;
	}




	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean isAlive() {
		return this.lives>0;
	}
	

	
	
}