package prr.core;

public class VideoCommunication extends InteractiveCommunication{

    public VideoCommunication(String idChegada,String idPartida, Client c){
        super(idChegada, idPartida);
        calculateVideoCost(c, this.getDuration());
        setType("VIDEO");
    }

    public void calculateVideoCost(Client c1, int duration){
        switch(c1.getClientLevel().name()){
            case("NORMAL"):{
                setCost(30*duration);
                return;
            }
            case("GOLD"):{
                setCost(20*duration);
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
        return("VIDEO"+"|"+Integer.toString(getIDComms())+"|"+returnIDPartida()+"|"+returnIDChegada()+"|"+
        String.valueOf(Math.round(getDuration())+"|"+String.valueOf(Math.round(getCost()))+"|"+getStatus()));

    }
}
