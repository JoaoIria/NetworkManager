package prr.app.terminals;

import prr.core.Network;
import prr.core.NetworkManager;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("idTerminal", Message.terminalKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String terminalID = stringField("idTerminal");
    //FIXME implement command"
    // create an instance of prr.app.terminal.Menu with the
    // selected Terminal and open it

   (new prr.app.terminal.Menu(_receiver.getNetwork(), _receiver.showTerminal(terminalID))).open();
  }
}
