package prr.app.client;

import prr.core.Network;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnidentifiedClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException,UnknownClientKeyException{
    String ClientID = stringField("ClientID"); 

    try{
      if(_receiver.findClientById(ClientID).getClientNotificationStatus() == true){
        _display.add(Message.clientNotificationsAlreadyEnabled());
        _display.display();
      }
      else{
        _receiver.activateClientNotifications(ClientID);
      }
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(ClientID);
    }
  }
}
