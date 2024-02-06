
package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {

	// Remember that a Sun is updated the very same cycle is added to the container
	public static final int SUN_COOLDOWN = 10+1;
	public static final int SUNCOINS=10;


	public Sun(GameWorld game, int col, int row) {
		
		super(game, col, row);
		lives=Sun.SUN_COOLDOWN;
	}

	@Override
	public boolean catchObject() {
		//System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

		lives=0;
		game.addSunCoins(SUNCOINS);
		update();
		return true;
	}

	@Override
	public boolean fillPosition() {
		return false;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {

		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {

		return false;
	}

	@Override
	public boolean isNpc() {

		return true;
	}

	@Override
	public boolean isAlive() {

		return lives>0;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void update() {
		
		lives--;
		if(!isAlive()) {
			
			onExit();
		}

	}

	@Override
	public void onEnter() {
		
		update();
	}

	@Override
	public void onExit() {
		
	}


}