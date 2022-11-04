package prr.core;

public class TextCommunication extends Communication{
    private String _message;
    /**
      * Constructor
      */ 
      
    public TextCommunication(String idChegada,String idPartida, String message, Client c){ 
        super(idChegada, idPartida);
        calculateTextCost(c, message);
        setStatus("FINISHED");
        _message = message;
        setType("TEXT");
    }


    public String getMessage(){
        return _message;
    }

    public int getSize(){
        return _message.length();
    }

    public void calculateTextCost(Client c1, String text){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                if(text.length() < 50){
                    setCost(10);
                    return;
                }
                if(text.length() >= 50 && text.length() < 100){
                    setCost(16);
                    return;
                }
                if(text.length() >= 100){
                    setCost(2*text.length());
                    return;
                }
            }
            case("GOLD"):{
                if(text.length()< 50){
                    setCost(10);
                    return;
                }
                if(text.length() >= 50 && text.length() < 100){
                    setCost(10);
                    return;
                }
                if(text.length() >= 100){
                    setCost(2*text.length());
                    return;
                }
            }
            case("PLATINUM"):{
                if(text.length() < 50){
                    setCost(0);
                    return;
                }
                if(text.length() >= 50 && text.length() < 100){
                    setCost(4);
                    return;
                }
                if(text.length() >= 100){
                    setCost(4);
                    return;
                }
            }
        }
    }


    @Override
    public String toString(){
        return("TEXT"+"|"+Integer.toString(this.getIDComms())+"|"+this.returnIDPartida()+"|"+returnIDChegada()+"|"+
        Integer.valueOf(_message.length())+"|"+String.valueOf(Math.round(getCost()))+"|"+getStatus());
    }
}
