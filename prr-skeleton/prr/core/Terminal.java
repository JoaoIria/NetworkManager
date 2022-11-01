package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import prr.core.exception.InvTerminalKeyException;
import prr.core.exception.UnkTerminalIdException;


/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  
  private Network _network;

  private String _clientId;
  private String _id;
  private double _debt;
  private double _payments;
  private TerminalMode _mode;
  private List <String> _friends;
  private List <Notification> _notifications;
  private List <Communication> _comunications;


   /**
   * Constructor
   */ 
  
  public Terminal(String id, String clientId){
    _id = id;
    _debt = 0;
    _payments = 0;
    _clientId = clientId;
    _mode = TerminalMode.IDLE;
    _friends = new ArrayList<>();
    _notifications = new ArrayList<>();
    _comunications = new ArrayList<>();
  }

  /**
   * Compares two Terminal Id's
   * 
   * @param t "Terminal".
   * 
   * @return 0 or 1
   */

   
  @Override
  public int compareTo(Terminal t){
      return Integer.valueOf(this._id).compareTo(Integer.valueOf(t._id));
  }

  /**
   * Gets the Terminal's Payments
   *
   * @return the Terminal's Payments
   */

  public double getTerminalPayments(){
    return _payments;
  }


  /**
   * Gets the Terminal's debt
   *
   * @return the Terminal's debt
   */

  public double getTerminalDebts(){
    return _debt;
  }


  /**
   * Gets the Terminal's mode : "IDLE", "OFF", "BUSY", "SILENCE"
   *
   * @return the Terminal's mode
   */

  public TerminalMode getTerminalMode(){
    return _mode;
  }


  /**
   * Gets the Terminal's Id
   *
   * @return the Terminal's Id
   */

  public String getTerminalID(){
    return _id;
  }

  /**
   * Gets the Client's Id to whom the Terminal is attached
   *
   * @return the Client's Id to whom the Terminal is attached
   */
  public String getTerminalClientID(){
    return _clientId;
  }


  /**
   * Gets the List of Notifications in the Terminal  
   *
   * @return the list of Notifications in the Terminal
   */

  public List <Notification> getNotificiations(){
    return _notifications;
  }


  /**
   * Adds a Notification to the Terminal's List of Notifications
   * 
   * @param n "Notification"
   *
   * @return void
   */ 

  public void addNotification(Notification n){
    _notifications.add(n);
  }


  /**
   * Deletes all the Notifications in the terminal's list of Notifications
   *
   * @return void
   */ 

  public void clearAllNotifications(){
    _notifications.clear();
  }


  /**
   * Alters the Terminal Mode to SILENCE
   *
   * @return void
   */ 

  public void setOnSilent(){
    _mode = TerminalMode.SILENCE;
  }
  

   /**
   * Alters the Termial Mode to OFF
   *
   * @return void
   */ 

  public void turnOff(){
    _mode = TerminalMode.OFF;
  }


  /**
   * gets the List of Comunications involving the Terminal
   *
   * @return the List of Comunications involving the Terminal
   */ 

  public List <Communication> getCommunications(){
    return _comunications;
  }


  /**
   * Gets the Terminal's Friend List
   *
   * @return the Terminal's Friend List
   */ 
   
  public List <String> getFriends(){
    return _friends;
  }
  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/

  public boolean canEndCurrentCommunication() {
    if(_mode.name().equals("BUSY")){
      for(Communication c: _comunications){
        if(c.returnIDPartida().equals(this._id)){
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  
  public boolean canStartCommunication() {
    if(_mode.name().equals("BUSY") || _mode.name().equals("OFF") ){
      return false;
    }
    return true;
  }

  public void addFriend(String TerminalFriend) throws InvTerminalKeyException{

    if(TerminalFriend.length() != 6){
      throw new InvTerminalKeyException();
    }
    if(!TerminalFriend.matches("[0-9]+")){
      throw new InvTerminalKeyException();
    };
    
    /* COMO VERIFICAR SE O TERMIANL ADICIONADO EXISTE??? */
    /* COMO FAZER PARA ELE N SE ADICIONAR A ELE MESMO???? */

    _friends.add(TerminalFriend);
  }
}