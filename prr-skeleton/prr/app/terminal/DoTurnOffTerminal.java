package prr.app.terminal;

import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnkTerminalIdException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
      if(_receiver.getTerminalMode().name().equals("OFF")){
        _display.add(Message.alreadyOff());
        _display.display();
        return;
      }
      else{
        _network.TurnOffTerminal(_receiver.getTerminalID());
      }
    }catch(UnkTerminalIdException utie){
      throw new UnknownClientKeyException(_receiver.getTerminalID());
    }
  }
}