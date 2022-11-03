package prr.app.client;

import prr.core.Network;

import prr.core.exception.UnidentifiedClientKeyException;
import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.NoNotificationsKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

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

    try{
    _display.addLine(_receiver.showClientById(key));
    for (String notification : _receiver.getNotifications(key)){
      _display.addLine(notification);
    }
    _display.display();
    }catch(UnidentifiedClientKeyException ucke){
    throw new UnknownClientKeyException(key);
    }
  }
}
