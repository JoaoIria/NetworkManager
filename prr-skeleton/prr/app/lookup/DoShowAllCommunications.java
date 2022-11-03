package prr.app.lookup;

import java.util.List;
import java.util.ArrayList;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME more imports if needed

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

  DoShowAllCommunications(Network receiver) {
    super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List <String> coms = new ArrayList<>(_receiver.showAllCommunications());

    for (String c : coms){
      _display.addLine(c);
    }
    _display.display();
  }  
}
