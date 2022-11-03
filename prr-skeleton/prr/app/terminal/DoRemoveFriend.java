package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.UnkTerminalIdException;

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("terminalKey", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("terminalKey");
    
    try{
      _network.removeFriend(_receiver.getTerminalID(), terminalKey);
    }catch(InvTerminalKeyException itke){
      throw new InvalidTerminalKeyException(terminalKey);
    }catch(UnkTerminalIdException utie){
      throw new UnknownTerminalKeyException(terminalKey);
    }
  }
}