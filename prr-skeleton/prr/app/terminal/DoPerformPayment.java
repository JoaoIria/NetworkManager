package prr.app.terminal;

import prr.core.exception.UnidentifiedClientKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import prr.core.exception.UnkCommunicationKeyException;
import prr.core.exception.UnkTerminalIdException;
import prr.app.exception.UnknownClientKeyException;
import prr.app.exception.UnknownCommunicationKeyException;
import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addStringField("commKey", Message.commKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    int commKey = Integer.valueOf(stringField("commKey"));
    Communication comm = null;
    for(Communication c: _network.getAllCommunications()){
      if(c.getIDComms() == commKey){
        comm = c;
      }
      else{
        _display.add(Message.invalidCommunication());
        _display.display();
        return;
      }
    }

    try{
      if(comm.getStatus().equals("FINISHED") && _receiver.getTerminalID().equals(comm.returnIDPartida()) &&
      comm.isPaid() == false){
        _network.MakePayment(commKey);
        return;
      }
      else{
        _display.add(Message.invalidCommunication());
        _display.display();
        return;
      }
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(_receiver.getTerminalClientID());
    }catch(UnkCommunicationKeyException unke){
      throw new UnknownCommunicationKeyException(commKey);
    }catch(UnkTerminalIdException utie){
      throw new UnknownTerminalKeyException(comm.returnIDPartida());
    }
  }
}
