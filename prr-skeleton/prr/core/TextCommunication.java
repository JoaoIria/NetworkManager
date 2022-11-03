package prr.core;

public class TextCommunication extends Communication{
    private String _message;
    /**
      * Constructor
      */ 
      
    public TextCommunication(String idChegada,String idPartida, String message){ 
        super(idChegada, idPartida);
        _message = message;
    }


    public String getMessage(){
        return _message;
    }

    public int getSize(){
        return _message.length();
    }

    /*public double computeCost(TariffPlan tariff){
        tariff.BasicPlan.calculateTextCost(findClient,this);
    }*/

    @Override
    public String toString(){
        return("TEXT"+"|"+Integer.toString(this.getIDComms())+"|"+this.returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(getUnits())+"|"+String.valueOf(getCost())+"|"+getStatus());
    }
}
