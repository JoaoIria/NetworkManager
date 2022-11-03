package prr.app.lookup;

import prr.core.Network;
import java.util.List;
import java.util.ArrayList;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

  DoShowClientsWithoutDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List <String> clientsNoDebts = new ArrayList<>(_receiver.showClientsWithoutDebts());
    for (String c : clientsNoDebts){
      _display.addLine(c);
    }
    _display.display();
  }
}
