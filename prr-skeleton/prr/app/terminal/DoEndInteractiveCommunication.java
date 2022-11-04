package prr.app.terminal;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.UnkTerminalIdException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addStringField("duration", Message.duration());

  }
  
  @Override
  protected final void execute() throws CommandException {
    int duration = Integer.parseInt(stringField("duration"));
    try{
      for(Communication c: _receiver.getCommunications()){
        if(c.getStatus().equals("ONGOING")){
          _network.endInteractiveCommunication(c.getType(), _receiver, duration);
          _network.changeClientStatus(_network.findClientById(_receiver.getTerminalClientID()));
          _display.add(Message.communicationCost(Math.round(c.getCost())));
          _display.display();
          return;
        }
      }
    }catch(UnidentifiedClientKeyException ucke){
      throw new UnknownClientKeyException(null);
    }catch(UnkTerminalIdException utie){
      throw new UnknownClientKeyException(null);
    }
  }
}