package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.app.exception.UnknownClientKeyException;
//FIXME add more imports if needed

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

  DoShowCommunicationsToClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
    addStringField("ClientID", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String key = stringField("ClientID");

    try{
      for(String c: _receiver.showClientReceivedComunications(key)){
        _display.addLine(c);
      }
      _display.display();
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(key);
    }
  }
}
