package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
		//new Zombie(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */

	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size();
	}

	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int col, int row) {
		
		if (!isValidZombie(zombieIdx)) {
			return null;
		}
		
		Zombie z= AVAILABLE_ZOMBIES.get(zombieIdx);


		if(z!=null) {
			
			 z = z.copy(game,col, row);

		}

		return z;
	}

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() {
	}
	
	

/*
	public static Zombie spawnZombie(GameWorld game, int col, int row) {
		
		Zombie z=new Zombie(game, col, row);
		
		z = z.copy(game,col, row);

		return z;
	}
	
*/
	
	
}
