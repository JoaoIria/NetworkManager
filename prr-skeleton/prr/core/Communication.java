package prr.core;

public class Communication {
    private String _idChegada;
    private String _idPartida;

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
    
}
