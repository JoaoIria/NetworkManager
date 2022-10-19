package prr.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client implements Comparable<Client>{
    
    protected String _key;
    protected String _name;
    protected int _taxNumber;
    protected ClientLevel _level;
    protected boolean _receiveNotifications;
    protected List <Terminal> _terminalList = new ArrayList<>();
    protected int _terminals;
    protected int _payments;
    protected int _debts;


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
        return Integer.valueOf(this._key).compareTo(Integer.valueOf(c._key));
    }
}
