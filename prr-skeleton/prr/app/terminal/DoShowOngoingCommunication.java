package prr.app.terminal;

import prr.core.Network;
import java.util.List;
import java.util.ArrayList;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    List <String> ongoingCommunications = new ArrayList<>(_receiver.getOngoingCommunications());
    for (String c : ongoingCommunications){
      _display.addLine(c);
    }
    _display.display();
  }

}
