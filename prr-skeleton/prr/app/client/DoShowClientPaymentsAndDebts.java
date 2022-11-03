package prr.app.client;

import prr.core.Client;
import prr.core.Network;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String ClientID = stringField("ClientID"); 
    
    try{
    _display.add(Message.clientPaymentsAndDebts(ClientID, Math.round(_receiver.getClientPayments(ClientID)), Math.round(_receiver.getClientDebts(ClientID))));
    _display.display();
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(ClientID);
    }
  }
}