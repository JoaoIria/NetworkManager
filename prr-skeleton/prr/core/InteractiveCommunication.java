package prr.core;

public abstract class InteractiveCommunication extends Communication{
    private int _duration;
    
    public InteractiveCommunication(String idChegada,String idPartida, int duration){
        super(idChegada, idPartida);
        _duration = duration;
    }
}
