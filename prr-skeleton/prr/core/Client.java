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
    private List <Terminal> _terminalList = new ArrayList<>();
    private int _terminals;
    private int _payments;
    private int _debts;


    public Client(String name,String key, int taxNumber){
        _name = name;
        _key = key;
        _taxNumber = taxNumber;
        _level = ClientLevel.NORMAL;
        _receiveNotifications = true;
        _terminals = 0;
        _payments = 0;
        _debts = 0;
    }
    
    public String getClientName(){
        return _name;
    }

    public List <Terminal> getTeminalList(){
        return _terminalList;
    }

    public String getClientID(){
        return _key;
    }

    public int getNumTerminals(){
        return _terminals;
    }

    public void addTerminal(Terminal t){
        _terminalList.add(t);
    }

    public void addNumTerminal(){
        _terminals ++;
    }

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
      +Integer.valueOf(this._payments)+"|"+Integer.valueOf(this._debts));
    }

    @Override
    public int compareTo(Client c){
        return String.valueOf(this._key).compareTo(String.valueOf(c._key));
    }
}
