package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Comparable<Terminal> /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  
  // FIXME define attributes

  private String _clientId;
  private String _id;
  private double _debt;
  private double _payments;
  private TerminalMode _mode;
  private List <String> _friends;
  private List <Notification> _notifications = new ArrayList<>();
  private List <Communication> _comunications = new ArrayList<>();

  // FIXME define contructor(s)
  public Terminal(String id, String clientId){
    _id = id;
    _debt = 0;
    _payments = 0;
    _clientId = clientId;
    _mode = TerminalMode.IDLE;
  }
  // FIXME define methods
  @Override
  public int compareTo(Terminal t){
      return Integer.valueOf(this._id).compareTo(Integer.valueOf(t._id));
  }

  public double getTerminalPayments(){
    return _payments;
  }

  public double getTerminalDebts(){
    return _debt;
  }

  public TerminalMode getTerminalMode(){
    return _mode;
  }

  public String getTerminalID(){
    return _id;
  }

  public String getTerminalClientID(){
    return _clientId;
  }

  public List <Notification> getNotificiations(){
    return _notifications;
  }

  public void addNotification(Notification n){
    _notifications.add(n);
  }

  public void clearAllNotifications(){
    _notifications.clear();
  }

  public void setOnSilent(){
    _mode = TerminalMode.SILENCE;
  }
  
  public void turnOff(){
    _mode = TerminalMode.OFF;
  }

  public List <Communication> getCommunications(){
    return _comunications;
  }

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
    // FIXME add implementation code
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
    // FIXME add implementation code
    if(_mode.name().equals("BUSY") || _mode.name().equals("OFF") ){
      return false;
    }
    return true;
  }
}
