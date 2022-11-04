package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import prr.core.exception.UnidentifiedClientKeyException;


/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;

  private String _clientId;
  private String _id;
  private double _debt;
  private double _payments;
  private String _type;
  private TerminalMode _inicialMode;
  private TerminalMode _mode;
  private List <String> _friends;
  private List <Notification> _notifications;
  private List <Communication> _comunications;
  private List <Notification> _waitingNotifications;


   /**
   * Constructor
   */ 
  
  public Terminal(String id, String clientId, String type){
    _id = id;
    _debt = 0;
    _payments = 0;
    _clientId = clientId;
    _mode = TerminalMode.IDLE;
    _friends = new ArrayList<>();
    _notifications = new ArrayList<>();
    _comunications = new ArrayList<>();
    _type = type;
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

  public TerminalMode getInicialTerminalMode(){
    return _inicialMode;
  }

  public void setInicialTerminalMode(TerminalMode mode){
     _inicialMode = mode;
  }

  public void setOnInicialTerminalMode(TerminalMode mode){
    _mode = mode;
  }

  public String getTerminalType(){
    return _type;
  }

  public List <String> getSortedFriends(){
    List <String> friends = new ArrayList<>();
    for(String s: _friends){
      friends.add(s);
    }
    Collections.sort(friends);
    return friends;
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

  public List <Notification> getNotificiationsWaiting(){
    return _waitingNotifications;
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


  public void setOnIdle(){
    _mode = TerminalMode.IDLE;
  }
  
  public void setOnBusy(){
    _mode = TerminalMode.BUSY;
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

  /* 
  public List<Communication> commsMadeByClient(String id){
    List<Communication> coms = new ArrayList<>();
    for(Communication c: _comunications){
      if(c.returnIDPartida().equals(id)){
        coms.add(c);
      }
    }
    return coms;
  }


  public List<Communication> commsReceivedByClient(String ClientID){
    List<Communication> coms = new ArrayList<>();
    for(Communication c: _comunications){
      if(c.returnIDChegada().equals(ClientID)){
        coms.add(c);
      }
    }
    return coms;
  }*/

  public void setPaymentTerminal(double payment){
    _payments += payment;
    _debt -= payment;
  }

  public void setDebtTerminal(double debt){
    _debt += debt;
  }


  public Communication makeSMS(Client c,Terminal toTerminal, String text) throws UnidentifiedClientKeyException{
    Communication comm = new TextCommunication(toTerminal.getTerminalID(),this.getTerminalID(),text, c);
    _comunications.add(comm);
    toTerminal._comunications.add(comm);
    return comm;
  }

  public Communication makeVoiceCall(Client c, Terminal toTerminal, int duration) throws UnidentifiedClientKeyException{
    Communication comm = new VoiceCommunication(toTerminal.getTerminalID(),this.getTerminalID(), c);
    _comunications.add(comm);
    toTerminal._comunications.add(comm);
    return comm;
  }

  public Communication makeVideoCall(Client c, Terminal toTerminal, int duration) throws UnidentifiedClientKeyException{
    Communication comm = new VideoCommunication(toTerminal.getTerminalID(),this.getTerminalID(), c);
    _comunications.add(comm);
    toTerminal._comunications.add(comm);
    return comm;
  }




















}