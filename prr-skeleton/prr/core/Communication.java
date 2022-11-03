package prr.core;

public abstract class Communication {
    private int _id = 0;
    private String _idChegada;
    private String _idPartida;
    private boolean _isPaid;
    private double _cost;
    private boolean _isOngoing;
    private double _units;
    private String _status; /*ONGOING OU FINISHED */


    public Communication(String idChegada,String idPartida){
        _idChegada = idChegada;
        _idPartida = idPartida;
        _id += 1;
    }
    
    public int getIDComms(){
        return _id;
    }

    public String getStatus(){
        return _status;
    }

    public double getUnits(){
        return _units;
    }

    public double getCost(){
        return _cost;
    }

    public boolean isPaid(){
        return _isPaid;
    }
    public boolean isOngoing(){
        return _isOngoing;
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


}
