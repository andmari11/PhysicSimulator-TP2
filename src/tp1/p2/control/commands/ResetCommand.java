package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level=null;

	private long seed=0L;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public ExecutionResult execute(GameWorld game){
		
		if(this.level==null || this.seed==0L) {
			
			game.reset(this.seed, this.level);
		}
		else {
			
			game.reset();
		}
		
		return new ExecutionResult(true);

	}

	@Override
	public Command create(String[] parameters) {
		ResetCommand ret;
		
		try {
			ret = (ResetCommand)this.clone();
		} 
		
		catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(parameters.length==3) {
			
			Level level=Level.valueOfIgnoreCase(parameters[1]);
			long seed= Long.parseLong (parameters[2] );
			
			ret=new ResetCommand(level, seed);
		}
		else {
			
			ret=new ResetCommand();
		}
		return ret;
	}

}
