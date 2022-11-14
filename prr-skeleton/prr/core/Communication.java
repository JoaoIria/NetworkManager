package prr.core;
import java.io.Serializable;

public abstract class Communication implements Serializable, Comparable<Communication>{
    private int _id;
    private String _idChegada;
    private String _idPartida;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private String _status; /*ONGOING OU FINISHED */
    private int _duration;
    private String _type;


    public Communication(String idChegada,String idPartida, int id){
        _idChegada = idChegada;
        _idPartida = idPartida;
        _cost = 0;
        _id = id;
    }

    public void calculateVideoCost(){
    };

    public int getIDComms(){
        return _id;
    }

    public String getStatus(){
        return _status;
    }

    public void setType(String type){
        _type = type;
    }

    public String getType(){
        return _type;
    }

    public void setDuration(int duration){
        _duration = duration;
    }

    public int getDuration(){
        return _duration;
    }

    public double getCost(){
        return _cost;
    }

    public boolean isPaid(){
        return _isPaid;
    }

    public void setPayment(){
        _isPaid = true;
    }

    public boolean isOngoing(){
        return _isOngoing;
    }

    public void setCost(double cost){
        _cost = cost;
    }

    public void setOnGoing(boolean is){
        _isOngoing = is;
    }

    public void setStatus(String Status){
        _status = Status;
    }
    /**
    * @return the communication departure terminal
    */

    public String returnIDPartida(){
        return _idPartida;
    }

    /**
    * @return the communication arrival terminal
    */

    public String returnIDChegada(){
        return _idChegada;
    }
    
    public Communication getCommunication() {
        return this;
    }

    @Override
    public int compareTo(Communication c){
        return String.valueOf(this._id).compareTo(String.valueOf(c._id));
    }
}
