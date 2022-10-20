package prr.core;

import java.lang.Math; 

public class BasicTerminal extends Terminal{

    public BasicTerminal(String id, String clientId){ //não sei se precisa de ter o clientID
        super(id, clientId);
    }


    @Override
    public String toString(){
        if(this._friends == null){
            return("BASIC"+"|"+this._id+"|"+this._clientId+"|"+this._mode.name()+"|"+Long.toString(Math.round(this._payments))
            +"|"+Long.toString(Math.round(this._debt)));
        }
        else{
            return("BASIC"+"|"+this._id+"|"+this._clientId+"|"+this._mode.name()+"|"+Long.toString(Math.round(this._payments))
            +"|"+Long.toString(Math.round(this._debt))+"|"+this._friends).toString();
        }
    }
}