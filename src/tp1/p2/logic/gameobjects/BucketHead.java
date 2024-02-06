package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {

	
	
	private static final int SPEED= 4;//numero de ciclos por casilla
	private static final int RESISTENCE=8;
	private static final int DAMAGE=1;
	
	
	
	public BucketHead (GameWorld game, int col, int row) {
		
		super(game, col, row);

		this.lives=BucketHead.RESISTENCE;
	}
	
	
	public BucketHead() {
	}

	public BucketHead copy(GameWorld game, int col, int row) {
		
		BucketHead z=new BucketHead(game, col, row);
		
		return z;
	} 

	@Override
	public boolean catchObject() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected String getSymbol() {

		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}

	@Override
	public String getDescription() {
		
		return Messages.ZOMBIE_DESCRIPTION;

	}
	
	public int getSpeed() {
		
		return BucketHead.SPEED;
	}
	
	public int getDamage() {
		
		return BucketHead.DAMAGE;
	}
	
	public int getEndurance() {
		
		return BucketHead.RESISTENCE;
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
