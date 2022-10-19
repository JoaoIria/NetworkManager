package prr.app.main;

import prr.core.NetworkManager;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed
import pt.tecnico.uilib.menus.CommandException;
import java.io.IOException;

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

  private String _filename;

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }
  
  @Override
  protected final void execute() throws CommandException {

    if(!(_receiver.hasFileName())){
      
      Form request = new Form();
      request.addStringField("answer", Message.newSaveAs());
      _filename = request.parse().stringField("answer");
    } else { 
      _filename = _receiver.getFilename();
    }
    
    try{
      _receiver.save(_filename);
    } catch(IOException e){
      e.printStackTrace();
    }
  }
}
