package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202208091753L;
  
  // FIXME define attributes

  protected String _clientId;
  protected String _id;
  protected double _debt;
  protected double _payments;
  protected TerminalMode _mode;
  protected String[] _friends;
  protected List <Notification> _notifications = new ArrayList<>();

  // FIXME define contructor(s)
  public Terminal(String id, String clientId){
    _id = id;
    _debt = 0;
    _payments = 0;
  }
  // FIXME define methods


  public void setOnSilent(){

  }
  
  public void turnOff(){
    
  }

  /**
   * Checks if this terminal can end the current interactive communication.
   *
   * @return true if this terminal is busy (i.e., it has an active interactive communication) and
   *          it was the originator of this communication.
   **/
  public boolean canEndCurrentCommunication() {
    // FIXME add implementation code
    return true;
  }

  /**
   * Checks if this terminal can start a new communication.
   *
   * @return true if this terminal is neither off neither busy, false otherwise.
   **/
  public boolean canStartCommunication() {
    // FIXME add implementation code
    return true;
  }
}
