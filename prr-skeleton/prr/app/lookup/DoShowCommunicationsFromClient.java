package prr.app.lookup;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnidentifiedClientKeyException;
//FIXME add more imports if needed

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

  DoShowCommunicationsFromClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
    addStringField("ClientID", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    String key = stringField("ClientID");

    try{
      for(String c: _receiver.showClientMadeComunications(key)){
        _display.addLine(c);
      }
      _display.display();
      return;
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(key);
    }
  }
}