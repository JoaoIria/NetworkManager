package prr.core;

public class FancyTerminal extends Terminal{

    public FancyTerminal(String id, String clientId){ //não sei se precisa de ter o clientID
        super(id, clientId);
    }


    @Override
    public String toString(){
        if(this.getFriends() == null){
            return("FANCY"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts())));
        }
        else{
            return("FANCY"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts()))+"|"+this.getFriends()).toString();
        }
    }
}