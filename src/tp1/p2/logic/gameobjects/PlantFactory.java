package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */




	public static Iterable<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}
	
	public static Plant isValidPlant(String plantName) {
		Plant p;

		
		for (int i=0;i< AVAILABLE_PLANTS.size();i++) {
			
			p=AVAILABLE_PLANTS.get(i);


			
			if(plantName.equals(p.getName()) ||plantName.equals(p.getSymbol().toLowerCase())) {

				return p;
			}
		}
		
		return null;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) {
		
		
		Plant plant=isValidPlant(plantName);

		
		if(plant!=null) {
			
			 plant = plant.copy(game,col, row);

		}
		else {
			
			//TODO devolver error
		}

		return plant;
	}
}
