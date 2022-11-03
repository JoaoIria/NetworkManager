package prr.app.terminal;

import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.UnkTerminalIdException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try{
        if(_receiver.getTerminalMode().name().equals("SILENCE")){
          _display.add(Message.alreadySilent());
          _display.display();
          return;
        }
        else{
          _network.setTerminalIdle(_receiver.getTerminalID());
        }
      }catch(UnkTerminalIdException utie){
        throw new UnknownClientKeyException(_receiver.getTerminalID());
      }
    
    }
}
