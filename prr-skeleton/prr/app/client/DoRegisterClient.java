package prr.app.client;

import prr.core.Network;
import prr.core.exception.SameClientKeyException;
import prr.app.exception.DuplicateClientKeyException;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register new client.
 */

class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("key", Message.key());
    addStringField("name", Message.name());
    addStringField("taxId", Message.taxId());
    //FIXME add command fields
  }
  
  @Override
  protected final void execute() throws CommandException, DuplicateClientKeyException {

    String _key = stringField("key");
    String _name = stringField("name");
    String _taxId = stringField("taxId");

    try{
      _receiver.registerClient(_key, _name, Integer.valueOf(_taxId));
    } catch (SameClientKeyException scke) {
      throw new DuplicateClientKeyException(_key);
    }
  }
}
