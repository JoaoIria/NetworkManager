package prr.app.terminals;


import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.ArrayList;
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
