package prr.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable, Comparable<Client>{

    private static final long serialVersionUID = 202208091753L;
    
    private String _key;
    private String _name;
    private int _taxNumber;
    private ClientLevel _level;
    private boolean _receiveNotifications;
    private List <Terminal> _terminalList;
    private int _terminals;
    private double _payments;
    private double _debts;



   /**
   * Constructor
   */ 

    public Client(String name,String key, int taxNumber){
        _name = name;
        _key = key;
        _taxNumber = taxNumber;
        _level = ClientLevel.NORMAL;
        _receiveNotifications = true;
        _terminals = 0;
        _payments = 0;
        _debts = 0;
        _terminalList = new ArrayList<>();
    }
    

   /**
   * Gets the Name of the Client
   *
   * @return the Name of the CLien
   */ 

    public ClientLevel getClientLevel(){
        return _level;
    }

    public String getClientName(){
        return _name;
    }

    public double getPayments(){
        return _payments;
    }

    public double getDebts(){
        return _debts;
    }

    public void setPaymentClient(double payment){
        _payments += payment;
        _debts -= payment;
    }

    public void setDebtClient(double debt){
        _debts += debt;
    }


   /**
   * Gets the List of Terminals associated to the Client
   *
   * @return the List of Terminals associated to the Client
   */ 

    public List <Terminal> getTeminalList(){
        return _terminalList;
    }


    public boolean getClientNotificationStatus(){
        return _receiveNotifications;
    }


    
   /**
   * Gets the Client's Id
   *
   * @return the Client's Id
   */ 

    public String getClientID(){
        return _key;
    }


   /**
   * Gets the number of Terminals associated to the Client
   *
   * @return the number of Terminals associated to the Client
   */ 

    public int getNumTerminals(){
        return _terminals;
    }


   /**
   * Adds a Terminal to the Client's Terminal List and increments the number of Tereminals assiciated to the Client
   *
   * @param t "Terminal"
   */ 

    public void addTerminal(Terminal t){
        _terminalList.add(t);
        _terminals ++;
    }


    public void activateNotificationsReception(){
        _receiveNotifications = true;
    }

    public void desactivateNotificationsReception(){
        _receiveNotifications = false;
    }


   /**
   * Transforms a Client into a String
   *
   * @return a String corresponding to the Client
   */ 

    @Override
    public String toString(){
        String notificationState;
        if (this._receiveNotifications){
            notificationState = "YES";
        }
        else{
            notificationState = "NO";
        }
        return("CLIENT"+"|"+this._key+"|"+this._name+"|"+Integer.valueOf(this._taxNumber)+"|"+
        this._level.name()+"|"+notificationState+"|"+Integer.valueOf(this._terminals)+"|"+
      +Math.round(this._payments)+"|"+Math.round(this._debts));
    }


   /**
   * Compares the Id of two Clients
   * 
   * @param c "Client"
   *
   * @return 0 or 1
   */ 

    @Override
    public int compareTo(Client c){
        return String.valueOf(this._key.toLowerCase()).compareTo(String.valueOf(c._key.toLowerCase()));
    }
}
