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




/**
 * Class Networks implements a serializable.
 */
public class Network implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private List <Client> _clients = new ArrayList<>();
  private List <Terminal> _terminals = new ArrayList<>();
  private List <Communication> _comunications = new ArrayList<>();

  /**
   * Constructor.
   */ 

  public Network(){
    _clients = new ArrayList<>();
    _terminals = new ArrayList<>();
    _comunications = new ArrayList<>();
  }

 /**
   * Register client on the network.
   * 
   * @param key "Client ID"
   * @param name "Client name"
   * @param taxNumber "Client Tax Number"
   * 
   * @throws SameClientKeyException if Client Id already exists
   * 
   * @return void
   */ 
  
  public void registerClient(String key, String name, int taxNumber) throws SameClientKeyException{
    Client _clientTemp = new Client(name, key ,taxNumber);
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        throw new SameClientKeyException();
      }
    }
    _clients.add(_clientTemp);
  }

 /**
   * Shows a client by his id.
   * 
   * @param key "Client ID"
   * 
   * @throws UnknownClientKeyException if Client Id doesn't exists
   * 
   * @return Client in String format
   */ 

  public String showClientById(String key) throws UnknownClientKeyException {
  
    for(Client c :_clients){
      if(c.getClientID().equals(key)){
        return(c.toString());
      }  
    }throw new UnknownClientKeyException(key);
  }

 /**
   * Adds a notification to a specific terminal.
   * 
   * @param t "Terminal"
   * @param notification "notification"
   * 
   * @return notification in terminal t
   */ 

  public void addNotifications(Terminal t, Notification notification){
    t.addNotification(notification);
  }

 /**
   * Clear all notifications from a specific terminal.
   * 
   * @param t "Terminal"
   * 
   * @return all notifications erased
   */ 

  public void clearNotifications(Terminal t){
    t.clearAllNotifications();
  }

 /**
   * Get all notifications from a specific terminal.
   * 
   * @param key "Terminal Client ID"
   * 
   * @return all notifications
   */ 

  public List<String> getNotifications(String key){


    List<String> getNots = new ArrayList<>();

    for(Terminal t: _terminals){
      if(t.getTerminalClientID().equals(key)){
        getNots.add(t.getNotificiations().toString());
        clearNotifications(t);
      }
    }
    return getNots;
  }

   /**
   * Show all clients from the network.
   * 
   * @return all clients
   */ 


  public List<String> showAllClients(){
    List<String> list = new ArrayList<>();
    Collections.sort(_clients);
    for (Client c: _clients){
      list.add(c.toString());
    }
    return list;
  }


 /**
   * Register client on the network.
   * 
   * @param mode "Terminal Mode"
   * @param idTerminal "Terminal ID"
   * @param idClient "Terminal Client ID"
   * 
   * @throws UnidentifiedClientKeyException if the client id doesn't exist
   * @throws SameTerminalKeyException if the terminal id already exists
   * @throws InvTerminalKeyException if the terminal id is invalid
   * @throws UnkTerminalIdException if terminal id doesn't exist
   * 
   * @return adds the terminal to the list of terminals in network
   * and to client list of terminals
   */ 


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
            return showTerminal(idTerminal);
          case "FANCY":
            _terminals.add(new FancyTerminal(idTerminal, idClient));
            c.addTerminal(new FancyTerminal(idTerminal, idClient));
            return showTerminal(idTerminal);
          default:
            return null;
        }
      } 
    }
    throw new UnidentifiedClientKeyException();
  }


   /**
   * Show the terminal by its id
   * 
   * @param idTerminal "Terminal ID"
   * 
   * @throws UnkTerminalIdException if terminal id doesn't exist
   * 
   * @return the terminal with the specific id
   */ 


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


   /**
   * Show all the terminals
   * 
   * @return the list of all terminals sorted
   */ 
  
  public List<String> showAllTerminals(){
    List<String> list = new ArrayList<>();
    Collections.sort(_terminals);
    for (Terminal t: _terminals){
      list.add(t.toString());
    }
    return list;
  }

   /**
   * Show all unused terminals
   * 
   * @return the list of all terminals 
   */ 

  public List<String> showUnusedTerminal(){
    List<String> list = new ArrayList<>();
    for(Terminal t : _terminals){
      if(t.getCommunications().isEmpty()){
        list.add(t.toString());
      }
    }
    return list;
  }

   /**
   * Adds a terminal friend to a terminal
   * 
   * @param s1 "Terminal ID form the one that will receive the friend"
   * @param s2 "Terminal ID form the one that will be added as a friend"
   */ 

  public void addFriend(String s1, String s2){

    for(Terminal t: _terminals){
      if (t.getTerminalID().equals(s1)){
          if(!t.getFriends().contains(s2)){
            t.getFriends().add(s2);
        }
      }
    }
  }


   /**  -------------------------------------------------------- **
   *                        NOT DONE YET 
   * ------------------------------------------------------------*/ 

  public void sendTextCommunication(Terminal t, String key, String msg){
  }

  
  
  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * 
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


    /**
   * @return Returns the network we are working in.
   */

  public Network getNetwork() {
    return this;
  }
}

