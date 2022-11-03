package prr.app.terminal;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnkTerminalIdException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
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
        _network.setTerminalSilence(_receiver.getTerminalID());
      }
    }catch(UnkTerminalIdException utie){
      throw new UnknownClientKeyException(_receiver.getTerminalID());
    }
  }
}
