package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
    
    public VoiceCommunication(String idChegada,String idPartida,int duration){
        super(idChegada, idPartida, duration);
    }
    

    @Override
    public String toString(){
        return("VOICE"+"|"+Integer.toString(getIDComms())+"|"+returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(getUnits())+"|"+String.valueOf(getCost())+"|"+getStatus());

    }

    public void computeCost(TariffPlan tarifario){

    }
}
