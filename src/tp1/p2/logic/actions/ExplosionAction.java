package tp1.p2.logic.actions;


import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private static final int[] DIRECCION = new int [] {1,0,-1}; 
	
	private int col;

	private int row;

	private int damage;
	
	private boolean attackZombies;

	public ExplosionAction(int col, int row, int damage, boolean attackZombies) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.attackZombies = attackZombies;
	}

	@Override
	public void execute(GameWorld game) {
		
		
		for(int i=0; i<DIRECCION.length; i++) {
			
			for(int j=0; j<DIRECCION.length;j++) {

				
				if(game.isValidZombiePosition(col+DIRECCION[i], row+DIRECCION[j])) {
					
					if(!game.isPositionEmpty(col+DIRECCION[i], row+DIRECCION[j])) {
						
						if(attackZombies)
							
							game.attackPlant(col+DIRECCION[i], row+DIRECCION[j], damage);
						else
							
							game.attackZombie(col+DIRECCION[i], row+DIRECCION[j], damage);		
						
					}
				}
	
			}
			
		}
	}
	


}
