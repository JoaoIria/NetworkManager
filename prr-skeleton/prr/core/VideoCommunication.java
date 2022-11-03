package prr.core;

public class VideoCommunication extends InteractiveCommunication{
    
    public VideoCommunication(String idChegada,String idPartida,int duration){
        super(idChegada, idPartida, duration);
    }


    @Override
    public String toString(){
        return("VIDEO"+"|"+Integer.toString(getIDComms())+"|"+returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(getUnits())+"|"+String.valueOf(getCost())+"|"+getStatus());

    }
}
