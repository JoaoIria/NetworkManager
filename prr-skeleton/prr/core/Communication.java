package prr.core;

public abstract class Communication {
    private int _id;
    private String _idChegada;
    private String _idPartida;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private String _status; /*ONGOING OU FINISHED */
    private int _duration;
    private String _type;
    private static int _comNum;


    public Communication(String idChegada,String idPartida){
        _idChegada = idChegada;
        _idPartida = idPartida;
        _cost = 0;
        incrementComNum();
        _id = _comNum;
    }


    public static void incrementComNum(){
        _comNum ++;
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
    
    /*@Override
    public String toString(){
        return(_type+"|"+Integer.toString(_id)+"|"+_idPartida+"|"+_idChegada+"|"+
        String.valueOf(_units)+"|"+String.valueOf(_price)+"|"+_status);
    }*/


  public Communication getCommunication() {
    return this;
  }

}
