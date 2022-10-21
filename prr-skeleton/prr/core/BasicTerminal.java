package prr.core;

import java.lang.Math; 

public class BasicTerminal extends Terminal{

    public BasicTerminal(String id, String clientId){ //não sei se precisa de ter o clientID
        super(id, clientId);
    }


    @Override
    public String toString(){
        if(this.getFriends() == null){
            return("BASIC"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts())));
        }
        else{
            return("BASIC"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts()))+"|"+this.getFriends()).toString();
        }
    }
}