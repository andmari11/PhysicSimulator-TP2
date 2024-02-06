package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;
		
		

		for (GameObject g : gameObjects) {
			
			
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {

				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead() {
		
		List <GameObject> vivos = new ArrayList<>();
		boolean ret=false;
		
		for (GameObject go : gameObjects) {

			if (!go.isAlive()) {
				
				go.onExit(); 
				ret=true;
			}
			else {
				
				vivos.add(go);
			}
		}
		
		this.gameObjects=vivos;
		
		return ret;	
		
	
	}
	
	public boolean catchObject(int col, int row) {
		boolean ret=false;
		GameObject go=getGameItemInPosition(col, row);
		
		while(go!=null && !ret) {
			
			ret=go.catchObject();
			go=getGameItemInPosition(col, row);
			
			removeDead();
		
		}
		
		return ret;

	}


	public void update() {


		for (GameObject g : gameObjects) {
			
			
			if (g.isAlive()) { 
				
				g.update();
			}
			else {
			
				g.onExit();
			}
		}

	}
	
	/*		for (GameObject g : gameObjects) {
			
			
			
			if (g.isAlive()) { 
				
				System.out.println(g.getClass());
				g.update();
			}
			else {
			
				g.onExit();
			}
		}*/
	
	public void add(GameObject go) {

		gameObjects.add(go);
		go.onEnter();

	}
	
	public boolean isPositionEmpty(int col, int  row) {
		
		boolean ret=true;
		GameObject go=getGameItemInPosition(col, row);
		
		if( go!=null) {
			
			ret=!go.fillPosition();
		}

		return ret;
	}
	
	public GameObject getGameItemInPosition(int col, int row) {
		
		//System.out.println(gameObjects.size());

		
		for(int i=0;i<gameObjects.size();i++) {
						
			if(gameObjects.get(i).isInPosition(col, row)){
				
				return gameObjects.get(i);
				
			}
			
		}

		return null;
	}
	
	public boolean fillPosition() {
		return true;
	}


	public boolean isFullyOccupied(int col, int row) {
		int i=0;
		boolean fullyOcuppied = false;

		while (i<gameObjects.size() && !fullyOcuppied) {
			GameObject g = gameObjects.get(i);
			if (g.isAlive() && g.isInPosition(col, row)) {
				fullyOcuppied = g.fillPosition();
			}
			i++;
		}

		return fullyOcuppied;
	}
}
