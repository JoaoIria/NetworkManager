package prr.app.terminals;

import java.util.Collections;

import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.core.Client;
import prr.core.Network;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

  DoShowAllTerminals(Network receiver) {
    super(Label.SHOW_ALL_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List <String> _terminalsString = new ArrayList<>(_receiver.showAllTerminals());

    for(String c: _terminalsString){
      _display.addLine(c);
    }
    _display.display();
  }
}
