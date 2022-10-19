package prr.app.main;

import prr.core.NetworkManager;
import prr.app.exception.FileOpenFailedException;
import prr.core.exception.UnavailableFileException;
/** import pt.tecnico.uilib.forms.Form; */
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

  DoOpenFile(NetworkManager receiver) {
    super(Label.OPEN_FILE, receiver);
    addStringField("filename", Message.openFile());
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException, FileOpenFailedException {

    String filename;

    try {
      filename = stringField("filename");
      _receiver.load(filename);
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe);
    }
  }
}
