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
import pt.tecnico.uilib.Display;
import prr.core.exception.UnidentifiedClientKeyException;
import prr.core.exception.SameTerminalKeyException;
import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.TerminalAlreadyOffException;
import prr.core.exception.UnkTerminalIdException;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private List <Client> _clients = new ArrayList<>();
  private List <Terminal> _terminals = new ArrayList<>();
  private List <Communication> _comunications = new ArrayList<>();

  
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
      if(c.getClientID().equals(key)){
        throw new SameClientKeyException();
      }
    }
    _clients.add(_clientTemp);
  }



  public String showClientById(String key) throws UnknownClientKeyException {
  
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        return(c.toString());
      }  
    }throw new UnknownClientKeyException(key);
  }



  public void addNotifications(Terminal t, Notification notification){
    t.addNotification(notification);
  }



  public void clearNotifications(Terminal t){
    t.clearAllNotifications();
  }



  public List<String> getNotifications(String key) /*throws NoNotificationsKeyException*/{


    List<String> getNots = new ArrayList<>();

    for(Terminal t: _terminals){
      if(t.getTerminalClientID().equals(key)){
        getNots.add(t.getNotificiations().toString());
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

  public Terminal registerTerminal(String mode,String idTerminal,String idClient) throws 
  UnidentifiedClientKeyException, SameTerminalKeyException, InvTerminalKeyException,UnkTerminalIdException{

    if(idTerminal.length() != 6){
      throw new InvTerminalKeyException();
    }
    
    for(Terminal t: _terminals){
      if(t.getTerminalID().equals(idTerminal)){
        throw new SameTerminalKeyException();
      }
    }

    for(Client c: _clients){
      if(c.getClientID().equals(idClient)){
        switch(mode){
          case "BASIC":
            _terminals.add(new BasicTerminal(idTerminal, idClient));
            c.addTerminal(new BasicTerminal(idTerminal, idClient));
            c.addNumTerminal();
            return showTerminal(idTerminal);
          case "FANCY":
            _terminals.add(new FancyTerminal(idTerminal, idClient));
            c.addTerminal(new FancyTerminal(idTerminal, idClient));
            c.addNumTerminal();
            return showTerminal(idTerminal);
          default:
            return null;
        }
      } 
    }
    throw new UnidentifiedClientKeyException();
  }

  public Terminal showTerminal(String idTerminal) throws UnkTerminalIdException{
    for(Terminal t : _terminals){
      if(t.getTerminalID().equals(idTerminal)){
        return t;
      }
    }
    throw new UnkTerminalIdException();
  }


  public void TurnOffTerminal(String idTerminal){
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
      if(t.getCommunications().isEmpty()){
        list.add(t.toString());
      }
    }
    return list;
  }

  public void addFriend(String s1, String s2){

    for(Terminal t: _terminals){
      if (t.getTerminalID().equals(s1)){
          if(!t.getFriends().contains(s2)){
            t.getFriends().add(s2);
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

