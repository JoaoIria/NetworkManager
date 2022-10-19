package prr.app.client;

import prr.core.Network;

import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.NoNotificationsKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("ClientID", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException, UnknownClientKeyException{

    String key = stringField("ClientID");
  
    _display.addLine(_receiver.showClientById(key));
    for (String notification : _receiver.getNotifications(key)){
      _display.addLine(notification);
    }
    _display.display();
    /*catch (NoNotificationsKeyException nnke){
    throw new UnknownClientKeyException(key);
    } */
  }
}
