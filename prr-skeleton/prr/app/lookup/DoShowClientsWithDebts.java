package prr.app.lookup;

import prr.core.Network;
import java.util.List;
import java.util.ArrayList;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

  DoShowClientsWithDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {
    List <String> clientsWithDebts = new ArrayList<>(_receiver.showClientsWithDebts());
    for (String c : clientsWithDebts){
      _display.addLine(c);
    }
    _display.display();
  }
}