package prr.app.terminal;

import prr.core.Network;
import java.util.List;
import java.util.ArrayList;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    List <String> ongoingCommunications = new ArrayList<>(_network.getOngoingCommunications(_receiver));

    if(ongoingCommunications.isEmpty()){
      _display.add(Message.noOngoingCommunication());
      _display.display();
      return;
    }

    for (String c : ongoingCommunications){
      _display.addLine(c);
    }
    _display.display();
  }

}
