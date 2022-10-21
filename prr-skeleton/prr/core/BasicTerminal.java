package prr.core;

import java.lang.Math; 

public class BasicTerminal extends Terminal{


   /**
     * Constructor
     */ 

    public BasicTerminal(String id, String clientId){ 
        super(id, clientId);
    }

    /**
      * @return the basic terminal in format of string
      */

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