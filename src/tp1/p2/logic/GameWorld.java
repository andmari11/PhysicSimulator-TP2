package tp1.p2.logic;

import java.lang.System.Logger.Level;

import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void addSun();

	boolean tryToCatchObject(int col, int row);

	boolean addItem(GameObject gameObject);

	void reset();

	void update();

	void playerQuits();

	boolean isPositionEmpty(int col, int row);

	boolean attackPlant(int col, int row, int damage);

	boolean attackZombie(int col, int row, int damage);

	boolean zombieArrived();

	boolean zombieDied();

	boolean isValidPlantPosition(int col, int row);

	boolean isValidZombiePosition(int col, int row);

	ExecutionResult addGameObject(GameObject gameObject);

	boolean tryToBuy(int cost);

	boolean isFullyOcuppied(int col, int row);

	void addSunCoins(int suncoins);

	void pushAction(ExplosionAction explosionAction);

	void reset(long seed, tp1.p2.control.Level level);

}
