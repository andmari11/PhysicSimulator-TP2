package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}
	
	
	@Override
	public ExecutionResult execute(GameWorld game) {
		Plant p=PlantFactory.spawnPlant(this.plantName, game,this.col, this.row);
		
		if(game.tryToBuy(p.getCost()) && p!=null) {
	
			return game.addGameObject(p);
		}

		return new ExecutionResult(false);
	}

	@Override
	public Command create(String[] parameters) {
		
		AddPlantCommand ret=new AddPlantCommand(true);
		String planta = null;
		int col = 0, row = 0;


		
		if(parameters.length<4) {
			
			System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
			
			
		}
		else if(!Character.isDigit(parameters[2].charAt(0)) || !Character.isDigit(parameters[3].charAt(0))) {
			
			System.out.println(error(Messages.INVALID_COMMAND));
			
		}
		else {
	

			 planta=parameters[1];
			 col=Integer.parseInt(parameters[2]);
			 row=Integer.parseInt(parameters[3]);
			
			try {


				ret= new AddPlantCommand(true);
				this.col=col;
				this.row=row;
				this.plantName=planta;
				ret = (AddPlantCommand)this.clone();
			} 
			
			catch (CloneNotSupportedException e) {
				
				System.out.println(error(Messages.INVALID_COMMAND));
				e.printStackTrace();
			}
				
		}
		
		return ret;
	}


}


