package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.UnkTerminalIdException;
import prr.app.exception.UnknownClientKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("destinationTerminal", Message.terminalKey());
    addOptionField("commType", Message.commType(), "VIDEO", "VOICE");
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("destinationTerminal");
    String commType = stringField("commType");

    try{
      if(terminalKey.equals(_receiver.getTerminalID())){
        return;
      }
      else{
        if(_network.showTerminal(terminalKey).getTerminalMode().name().equals("OFF")){
          _display.add(Message.destinationIsOff(terminalKey));
          _display.display();
          return;}
        if(_network.showTerminal(terminalKey).getTerminalMode().name().equals("BUSY")){
          _display.add(Message.destinationIsBusy(terminalKey));
          _display.display();
          return;}
        if(_network.showTerminal(terminalKey).getTerminalMode().name().equals("SILENCE")){
          _display.add(Message.destinationIsSilent(terminalKey));
          _display.display();
          return;}
        else{
          switch(commType){
            case("VIDEO"):{
              if(_network.showTerminal(terminalKey).getTerminalType().equals("BASIC")){
                _display.add(Message.unsupportedAtDestination(terminalKey, commType));
                _display.display();
              }
              if(_receiver.getTerminalType().equals("BASIC")){
                _display.add(Message.unsupportedAtOrigin(terminalKey, commType));
                _display.display();
              }
              else{
              _network.sendVideoCommunication(_receiver, terminalKey, 0);
              return;
              }
            }
            case("VOICE"):{
              _network.sendVoiceCommunication(_receiver, terminalKey, 0);
              return;
              }
            }
          }
        }
      }catch(UnkTerminalIdException utie){
        throw new UnknownTerminalKeyException(terminalKey);
      }catch(UnidentifiedClientKeyException ucke){
        try {
          throw new UnknownClientKeyException(_network.showTerminal(terminalKey).getTerminalClientID());
        } catch (UnkTerminalIdException e) {
          throw new UnknownTerminalKeyException(terminalKey);
      }
    }
  }
}
