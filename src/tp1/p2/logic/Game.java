package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld {

	private long seed;
	
	private Random rand;

	private Level level;
	
	private int cycle;

	private GameObjectContainer container;

	private Deque<GameAction> actions;

	private boolean playerQuits=false;

	private ZombiesManager zombiesManager;
	
	private SunsManager sunsManager;

	private int suncoins=0;
	public static final int INICIAL_SUNCOINS=500;
	
	public Game(long seed, Level level) {
		this.seed = seed;
		this.level = level;

		reset();
	}


	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */
	@Override
	public void update() {

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		// TODO add your code here

		// 3. Game object updates
		zombiesManager.update();
		container.update();
		sunsManager.update();
		
		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			

			// 4. Remove dead
			 deadRemoved = this.container.removeDead();
			// 5. execute pending actions
			executePendingActions();

		}
		


		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();

	}
	
	
	/*
	 	
	//actualiza los objetos del juego
	@Override
	public void update() {
 	
		//sunflowers.update();
		//peashooters.update();
		zombiesManager.update();
		//System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		container.update();

		this.cycle++;
	}
	*/
	
	@Override
	public void pushAction(ExplosionAction explosionAction) {
		
		this.actions.addLast(explosionAction);
	}

	
	
	@Override
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}
	
	

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	//resetea cambiando la semilla y la dificultad
	@Override
	public void reset(long seed, Level level) {

		cycle=0;
		suncoins= INICIAL_SUNCOINS;
		this.rand = new Random(seed);
		this.level=level;
		
		zombiesManager= new ZombiesManager(this, level, rand);
		sunsManager = new SunsManager(this, rand);
		container= new GameObjectContainer();
		actions = new ArrayDeque<>();

		
	}
	
	
	//resetea sin cambiar la semilla y la dificultad
	@Override
	public void reset() {

		cycle=0;
		suncoins= INICIAL_SUNCOINS;
		this.rand = new Random(this.seed);
		
		zombiesManager= new ZombiesManager(this, level, rand);
		container= new GameObjectContainer();
		sunsManager = new SunsManager(this, rand);
		actions = new ArrayDeque<>();

		//sunflowers= new SunflowerList(0);
		//peashooters= new PeashooterList(0);
		
	}

	public boolean isFinished() {
		// true si termina el juego
		boolean ret=this.isPlayerDied() || this.allZombiesDied();

		return ret;
	}
	
	@Override
	public boolean isPlayerDied() {
		boolean ret=(this.zombieArrived());

		return ret;
	}

	//devuelve true si el jugador quiere salir del juego
	@Override
	public boolean isPlayerQuits() {

		return this.playerQuits;
	}
	
	@Override
	public void playerQuits() {

		this.playerQuits=true;
	}

	//devuelve true si la posicion esta libre
	@Override
	public boolean isPositionEmpty(int col, int row) {
		boolean ret=false;
		
		if(this.container.isPositionEmpty(col, row)) {
			
			ret=true;
		}
		
		return ret;
	}
	
	//devuelve true si la poscion es valida
	private static boolean isPositionInLimits(int col, int row) {
		
		boolean ret=false;
		
		if(col<Game.NUM_COLS && row<Game.NUM_ROWS) {
			
			ret=true;
		}
		
		
		return ret;
	}	
	

	@Override
	public int getCycle() {
		return this.cycle;
	}



	@Override
	public void addSunCoins(int suncoins) {
		
		this.suncoins+=suncoins;
	}
	
	@Override
	public int getSuncoins() {
		
		return this.suncoins;
	}
	
	//devuelve el numero de zombies por generar
	@Override
	public int getRemainingZombies() {
			
		return this.zombiesManager.getRemainingZombies();
	}
	
	//dada una poscion devuelve el string q ocupa la casilla
	@Override
	public String positionToString(int col, int row) {
		

		return container.positionToString(col, row);
	}

	//todos los zombies del juego han muerto
	@Override
	public boolean allZombiesDied() {
		

		return this.zombiesManager.allZombiesDied() && this.zombiesManager.getRemainingZombies()==0;
	}
	

	//devuelve true si ha sido posible atacar una planta
	@Override
	public boolean attackPlant(int col, int row, int damage) {

		boolean ret= false;
		//si no existe ninguna planta en la posicion las funciones devuelven null
		GameItem go= this.container.getGameItemInPosition(col, row) ;

		if(go!=null) {

			ret= go.receiveZombieAttack(damage);
		}
		
		return ret;
	}
	
	
	@Override
	//devuelve true si ha sido posible atacar a un zombie
	public boolean attackZombie(int col, int row, int damage) {
	    boolean ret=false;

	    GameItem zombie= this.container.getGameItemInPosition(col, row);
	    
	    if(zombie!=null && zombie.receivePlantAttack(damage)) {

	    	ret=true;
	
	    }
	    
		return ret;
	}

	public boolean execute(Command command) {
		
		ExecutionResult commandResult = command.execute(this);
		return commandResult.draw();
	}


	@Override
	public ExecutionResult addGameObject(GameObject gameObject) {
		
		ExecutionResult ret= new ExecutionResult(false);
		

		
		if(this.isPositionEmpty(gameObject.getCol(), gameObject.getRow())){
			


			container.add(gameObject);
			ret=new ExecutionResult(true);
		}
		return ret;
	}

	@Override
	public boolean tryToBuy(int cost) {
		
		boolean ret=false;
		
		if(this.suncoins >= cost) {
			
			ret=true;
			this.suncoins-=cost;
		}
	
		
		return ret;
	}

	@Override
	public boolean zombieArrived() {
		
		return false;
	}

	@Override
	public boolean isValidPlantPosition(int col, int row) {
		
		return Game.isPositionInLimits(col, row) && this.isPositionEmpty(col, row);
	}

	@Override
	public boolean isValidZombiePosition(int col, int row){
		
		return col==Game.NUM_COLS && this.isPositionEmpty(col, row);
	}


	@Override
	public void addSun() {
		
		sunsManager.addSun();
		
	}

	@Override
	public boolean zombieDied() {
		return this.zombiesManager.allZombiesDied();
	}


	@Override
	public boolean tryToCatchObject(int col, int row) {

		
		return container.catchObject(col, row);
	}


	@Override
	public boolean addItem(GameObject gameObject) {
		container.add(gameObject);
		return true;
	}




	@Override
	public int getGeneratedSuns() {
		
		
		return sunsManager.getGeneratedSuns();
	}


	@Override
	public int getCaughtSuns() {
		
		return sunsManager.getCatchedSuns();
	}


	

}
