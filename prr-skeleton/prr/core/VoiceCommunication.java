package prr.core;

public class VoiceCommunication extends InteractiveCommunication{
    
    public VoiceCommunication(String idChegada,String idPartida,Client c,int CommsId){
        super(idChegada, idPartida,CommsId);
        calculateVoiceCost(c, this.getDuration());
        setType("VOICE");
    }
    
    public void calculateVoiceCost(Client c1, int duration){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                setCost(20*duration);
                return;
            }
            case("GOLD"):{
                setCost(10*duration);
                return;
            }
            case("PLATINUM"):{
                setCost(10*duration);
                return;
            }
        }
    }


    @Override
    public String toString(){
        return("VOICE"+"|"+Integer.toString(getIDComms())+"|"+returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(Math.round(getDuration())+"|"+String.valueOf(Math.round(getCost()))+"|"+getStatus()));

    }

}
