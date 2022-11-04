package prr.core;

public class FancyTerminal extends Terminal{


    /**
      * Constructor
      */ 
      
    public FancyTerminal(String id, String clientId){ 
        super(id, clientId,"FANCY");
    }

    /**
      * @return the fancy terminal in format of string
      */

    @Override
    public String toString(){
        if(this.getFriends().isEmpty()){
            return("FANCY"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts())));
        }
        else{
            return("FANCY"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts()))+"|"+
            this.getFriends().toString().substring(1, getFriends().toString().length()-1).replace(" ", "")).toString();
        }
    }
}