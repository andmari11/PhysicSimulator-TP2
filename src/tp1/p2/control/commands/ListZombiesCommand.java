package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class ListZombiesCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_LIST_ZOMBIES_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_LIST_ZOMBIES_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_LIST_ZOMBIES_HELP;
	}

	@Override
	public ExecutionResult execute(GameWorld game) {
		System.out.println(Messages.AVAILABLE_ZOMBIES);
		// TODO cambiar a todas las plantas
		
		//get description de todas las plantas
		System.out.println(Messages.ZOMBIE_DESCRIPTION);

		return new ExecutionResult(false);
	}
}
