package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnidentifiedClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException,UnknownClientKeyException {
    String ClientID = stringField("ClientID"); 

    try{
      if(_receiver.findClientById(ClientID).getClientNotificationStatus() == false){
        _display.add(Message.clientNotificationsAlreadyDisabled());
        _display.display();
      }
      else{
        _receiver.desactivateClientNotifications(ClientID);
      }
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(ClientID);
    }
  }
}
