package prr.app.client;

import java.util.LinkedList;
import java.util.List;

import prr.core.Client;
import prr.core.Network;
import java.util.ArrayList;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show all clients.
 */
class DoShowAllClients extends Command<Network> {

  DoShowAllClients(Network receiver) {
    super(Label.SHOW_ALL_CLIENTS, receiver);
  }
  

  @Override
  protected final void execute() throws CommandException {
    List <String> _clientString = new ArrayList<>(_receiver.showAllClients());

    for (String c : _clientString){
      _display.addLine(c);
    }
    _display.display();
  }
}
