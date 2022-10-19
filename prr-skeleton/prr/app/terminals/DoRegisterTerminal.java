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


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  protected List <String> types = new ArrayList<>();

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("terminalKey", Message.terminalKey());
    addStringField("terminalType", Message.terminalType());
    addStringField("clientKey", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command

    types.add("BASIC");
    types.add("FANCY");
    
    String _terminaKey = stringField("terminalKey");
    String _terminalType = stringField("terminalType");
    while(!types.contains(_terminalType)){
      _terminalType = stringField("terminalType");
    }
    String _clientKey = stringField("clientKey");

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
