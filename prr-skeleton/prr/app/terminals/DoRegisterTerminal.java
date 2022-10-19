package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.SameTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("terminalKey", Message.terminalKey());
    addStringField("terminalType", Message.terminalType());
    if (!stringField("terminalType").equals("BASIC") || !stringField("terminalType").equals("FANCY")){
      addStringField("terminalType", Message.terminalType());
    }
    addStringField("clienKey", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    
    String _terminaKey = stringField("terminalKey");
    String _terminalType = stringField("terminalType");
    String _clientKey = stringField("clienKey");

    try{
      _receiver.registerTerminal(_terminaKey, _terminalType, _clientKey);
    }catch(InvTerminalKeyException itke){
      throw new InvalidTerminalKeyException(_clientKey);
    }catch(SameTerminalKeyException stke){
      throw new DuplicateTerminalKeyException(_clientKey);
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(_clientKey);
    }
  }
}
