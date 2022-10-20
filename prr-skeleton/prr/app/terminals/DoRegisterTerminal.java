package prr.app.terminals;

import prr.core.Network;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.UnkTerminalIdException;
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

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    //FIXME add command fields
    addStringField("terminalKey", Message.terminalKey());
    addOptionField("terminalType", Message.terminalType(), "FANCY", "BASIC");
    addStringField("clientKey", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException{
    String terminalKey = stringField("terminalKey");
    String terminalType = stringField("terminalType");
    String clientKey = stringField("clientKey");

    try{
      _receiver.registerTerminal(terminalType, terminalKey, clientKey);
    }catch(InvTerminalKeyException itke){
      throw new InvalidTerminalKeyException(terminalKey);
    }catch(SameTerminalKeyException stke){
      throw new DuplicateTerminalKeyException(terminalKey);
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(clientKey);
    } catch(UnkTerminalIdException ukie){
      throw new UnknownTerminalKeyException(terminalKey);
    }
  }
}
