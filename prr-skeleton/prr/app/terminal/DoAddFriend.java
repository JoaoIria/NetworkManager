package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.UnkTerminalIdException;
/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("terminalKey", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("terminalKey");
    
    try{
      if(_receiver.getTerminalID().equals(terminalKey)){
        return;
        /* throw new UnknownTerminalKeyException(terminalKey); QUE MSG É SUPOSTO APARECER QUANDO ADICIONA O MSM TERMINAL? */
      }
      else{
        _network.addFriend(_receiver.getTerminalID(), terminalKey);
      }
    }catch(InvTerminalKeyException itke){
      throw new InvalidTerminalKeyException(terminalKey);
    }catch(UnkTerminalIdException utie){
      throw new UnknownTerminalKeyException(terminalKey);
    }
  }
}