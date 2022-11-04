package prr.app.terminal;

import prr.core.Network;
import prr.core.Notification;
import prr.core.Terminal;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.UnkTerminalIdException;

import java.util.List;
import java.util.ArrayList;

import prr.app.exception.UnknownClientKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("destinationTerminal", Message.terminalKey());
    addStringField("message",Message.textMessage());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String terminalKey = stringField("destinationTerminal");
    String text = stringField("message");
    
    try{
      if(terminalKey.equals(_receiver.getTerminalID())){
        return;
      }
      else{
        if(_network.showTerminal(terminalKey).getTerminalMode().name().equals("OFF")){
          _network.addNotifications(_receiver,new Notification("I2O",_receiver.getTerminalID(),terminalKey));
          _display.add(Message.destinationIsOff(terminalKey));
          _display.display();
          return;
        }
        else{
        _network.sendTextCommunication(_receiver, terminalKey, text);
        return;
        }
      }
    }catch(UnidentifiedClientKeyException ucke){
      try {
        throw new UnknownClientKeyException(_network.showTerminal(terminalKey).getTerminalClientID());
      } catch (UnkTerminalIdException e) {
        throw new UnknownTerminalKeyException(terminalKey);
      }
    }catch(UnkTerminalIdException utie){
      throw new UnknownTerminalKeyException(terminalKey);
    }
  } 
} 
