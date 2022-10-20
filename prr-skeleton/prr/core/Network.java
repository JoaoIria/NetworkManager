package prr.core;
import java.io.Serializable;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;

import prr.app.exception.UnknownClientKeyException;
import prr.core.exception.SameClientKeyException;
import prr.core.exception.NoNotificationsKeyException;
import prr.core.exception.UnrecognizedEntryException;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.SameTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  protected List <Client> _clients = new ArrayList<>();
  protected List <Terminal> _terminals = new ArrayList<>();
  protected List <Communication> _comunications = new ArrayList<>(); 
  
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  

  /**
   * Constructor.
   */ 

  public Network(){
    _clients = new ArrayList<>();
    _terminals = new ArrayList<>();
    _comunications = new ArrayList<>();
  }


  
  public void registerClient(String key, String name, int taxNumber) throws SameClientKeyException{
    Client _clientTemp = new Client(name, key ,taxNumber);
    for(Client c :_clients){
      if(c._key.equals(key)){
        throw new SameClientKeyException();
      }
    }
    _clients.add(_clientTemp);
  }



  public String showClientById(String key) throws UnknownClientKeyException {
  
    for(Client c :_clients){
      if(c._key.equals(key)){
        return(c.toString());
      }  
    }throw new UnknownClientKeyException(key);
  }



  public void addNotifications(Terminal t, Notification notification){
    t._notifications.add(notification);
  }



  public void clearNotifications(Terminal t){
    t._notifications.clear();
  }



  public List<String> getNotifications(String key) /*throws NoNotificationsKeyException*/{


    List<String> getNots = new ArrayList<>();

    for(Terminal t: _terminals){
      if(t._clientId.equals(key)){
        getNots.add(t._notifications.toString());
        clearNotifications(t);
      }
    }
    return getNots;
  }


  public List<String> showAllClients(){
    List<String> list = new ArrayList<>();
    Collections.sort(_clients);
    for (Client c: _clients){
      list.add(c.toString());
    }
    return list;
  }

  public Terminal registerTerminal(String idTerminal,String mode,String idClient) throws 
  UnidentifiedClientKeyException, SameTerminalKeyException, InvTerminalKeyException{

    if(idTerminal.length() != 6){
      throw new InvTerminalKeyException();
    }

    for(Client c: _clients){
      if(c._key.equals(idClient)){
        switch(mode){
          case "BASIC":
            _terminals.add(new BasicTerminal(idTerminal, idClient));
            c._terminalList.add(new BasicTerminal(idTerminal, idClient));
            c._terminals ++;
            return showTerminal(idTerminal);
          case "FANCY":
            _terminals.add(new FancyTerminal(idTerminal, idClient));
            c._terminalList.add(new FancyTerminal(idTerminal, idClient));
            c._terminals ++;
            return showTerminal(idTerminal);
          default:
            return null;
        }
      } 
    }
    for(Terminal t: _terminals){
      if(t._id.equals(idTerminal)){
        throw new SameTerminalKeyException();
      }
    }
    throw new UnidentifiedClientKeyException();
  }

  public Terminal showTerminal(String idTerminal){
    for(Terminal t : _terminals){
      if(t._id.equals(idTerminal)){
        return t;
      }
    }
    return null;
  }

  
  public List<String> showAllTerminals(){
    List<String> list = new ArrayList<>();
    Collections.sort(_terminals);
    for (Terminal t: _terminals){
      list.add(t.toString());
    }
    return list;
  }

  public List<String> showUnusedTerminal(){
    List<String> list = new ArrayList<>();
    for(Terminal t : _terminals){
      if(t._comunications.isEmpty()){
        list.add(t.toString());
      }
    }
    return list;
  }

  public void addFriend(String s1, String s2){

    for(Terminal t: _terminals){
      if (t._id.equals(s1)){
          if(!t._friends.contains(s2)){
            t._friends.add(s2);
          }
        
      }
    }
  }



  public void sendTextCommunication(Terminal t, String key, String msg){
   /** FORM?? */
  }
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    Parser parser = new Parser(this);
    try{
      parser.parseFile(filename);
    } catch(IOException | UnrecognizedEntryException e){
      throw e;
    } 
  }

  public Network getNetwork() {
    return this;
  }
}

