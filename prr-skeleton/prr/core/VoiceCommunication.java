package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
    
    public VoiceCommunication(String idChegada,String idPartida,int duration,Client c){
        super(idChegada, idPartida, duration);
        calculateVoiceCost(c, duration);
    }
    

    public double calculateVoiceCost(Client c1, int duration){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                setCost(20*duration);
            }
            case("GOLD"):{
                setCost(10*duration);
            }
            case("PLATINUM"):{
                setCost(10*duration);
            }
        }
        return 0;
    }

    @Override
    public String toString(){
        return("VOICE"+"|"+Integer.toString(getIDComms())+"|"+returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(getDuration())+"|"+String.valueOf(getCost())+"|"+getStatus());

    }

    public void computeCost(TariffPlan tarifario){

    }
}
