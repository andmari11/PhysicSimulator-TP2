package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public ExecutionResult execute(GameWorld game) {
		
		if(!caughtSunThisCycle)
		
			caughtSunThisCycle= game.tryToCatchObject(col, row);
		

		
		return new ExecutionResult(true);
	}

	@Override
	public Command create(String[] parameters) {
		
		//CatchCommand aux=null;
		
		if(parameters.length<3) {
			
			System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
			
		}
		else if(!Character.isDigit(parameters[1].charAt(0)) || !Character.isDigit(parameters[2].charAt(0))) {
			
			System.out.println(error(Messages.INVALID_COMMAND));
		}
		else {
			

	
			 this.col=Integer.parseInt(parameters[1]);
			 this.row=Integer.parseInt(parameters[2]);
			 

			
		}
		
		return this;
	}

}
